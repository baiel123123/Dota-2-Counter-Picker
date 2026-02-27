package com.dotacp.counterpicker.application;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;

@Service
public class MatchupService {

    private final RestClient restClient;

    private final Map<String, Long> heroIdMap = new HashMap<>();
    private final Map<Long, String> heroNameByIdMap = new HashMap<>();
    private final Map<Long, List> heroRoleByIdMap = new HashMap<>();



    public List<CounterHeroDTO> getTopCounters(String heroName, String role) {
        List<CounterHeroDTO> counters = new ArrayList<>();

        Long heroId = heroIdMap.get(heroName.toLowerCase());

        if (heroId == null) {
            throw new RuntimeException("Герой " + heroName + " не найден в OpenDota!");
        }

        OpenDotaMatchup[] response = restClient.get()
                .uri("/heroes/" + heroId + "/matchups")
                .retrieve()
                .body(OpenDotaMatchup[].class);

        if (response != null) {
            for (int i = 0; i < response.length; i++) {
                OpenDotaMatchup odData = response[i];

                double totalGames = odData.getGames_played();
                double enemyWins = totalGames - odData.getWins();

                double K = 50.0;

                double adjustedWinrate = ((enemyWins + K) / (totalGames + (K * 2))) * 100.0;

                double winrate = Math.round(adjustedWinrate * 100.0) / 100.0;

                String enemyName = heroNameByIdMap.get(odData.getHero_id());
                List enemyRoles = heroRoleByIdMap.get(odData.getHero_id());

                counters.add(new CounterHeroDTO(
                        "Враг с ID: " + odData.getHero_id(),
                        "Герой " + enemyName,
                        "Роль: " + enemyRoles,
                        winrate,
                        odData  .getGames_played()
                ));
            }
        }

        return counters.stream()
                .filter(dto -> role == null || dto.getRole().toLowerCase().contains(role.toLowerCase()))
                .sorted(Comparator.comparing(CounterHeroDTO::getWinrate).reversed())
                .toList();
    }
}