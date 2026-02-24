package com.dotacp.counterpicker.application;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;

@Service
public class MatchupService {

    private final RestClient restClient;
    // Наш пустой словарь
    private final Map<String, Long> heroIdMap = new HashMap<>();
    private final Map<Long, String> heroNameByIdMap = new HashMap<>();

    public MatchupService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.opendota.com/api")
                .build();
    }

    @Data
    public static class OpenDotaHero {
        private Long id;
        private String localized_name;
    }

    @Data
    public static class OpenDotaMatchup {
        private Long hero_id;
        private Integer games_played;
        private Integer wins;
    }

    @PostConstruct
    public void initHeroMap() {
        System.out.println("Начинаем загрузку героев из OpenDota...");

        OpenDotaHero[] heroes = restClient.get()
                .uri("/heroes")
                .retrieve()
                .body(OpenDotaHero[].class);

        if (heroes != null) {
            for (OpenDotaHero hero : heroes) {
                heroIdMap.put(hero.getLocalized_name().toLowerCase(), hero.getId());
                heroNameByIdMap.put(hero.getId(), hero.getLocalized_name());
            }
            System.out.println("Успех! Загружено героев в память: " + heroIdMap.size());
        }
    }

    public List<CounterHeroDTO> getTopCounters(String heroName) {
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

                counters.add(new CounterHeroDTO(
                        "Враг с ID: " + odData.getHero_id(),
                        "Герой " + enemyName,
                        "General",
                        winrate,
                        odData.getGames_played()
                ));
            }
        }

        return counters.stream()
                .sorted(Comparator.comparing(CounterHeroDTO::getWinrate).reversed())
                .toList();
    }
}