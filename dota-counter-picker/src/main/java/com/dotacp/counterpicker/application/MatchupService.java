package com.dotacp.counterpicker.application;

import com.dotacp.counterpicker.domain.Hero;
import com.dotacp.counterpicker.infrastructure.*;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchupService {
    private final OpenDotaClient openDotaClient;
    private final HeroRepository heroRepository;

    private final Map<String, Long> heroIdMap = new HashMap<>();
    private final Map<Long, String> heroNameByIdMap = new HashMap<>();
    private final Map<Long, List> heroRoleByIdMap = new HashMap<>();

    public MatchupService(OpenDotaClient openDotaClient, HeroRepository heroRepository) {
        this.openDotaClient = openDotaClient;
        this.heroRepository = heroRepository;
    }

    @PostConstruct
    public void initHeroMap() {
        System.out.println("Шаг 1: Проверка базы данных...");
        if (heroRepository.count() == 0) {
            System.out.println("База пуста! Качаем из OpenDota...");
            OpenDotaHero[] dotaHeroes = openDotaClient.fetchAllHeroes();

            if (dotaHeroes != null) {
                for (OpenDotaHero dotaHero : dotaHeroes) {
                    Hero hero = new Hero();

                    hero.setId(dotaHero.getId());
                    hero.setName(dotaHero.getName());
                    hero.setLocalizedName(dotaHero.getLocalized_name());

                    hero.setPrimaryAttr(dotaHero.getPrimary_attr());
                    hero.setAttackType(dotaHero.getAttack_type());
                    hero.setRoles(dotaHero.getRoles());

                    heroRepository.save(hero);
                }
            }
        }

        // 2. Load from Database into HashMaps (The Cache)
        System.out.println("Шаг 2: Загрузка героев из БД в память (Кэш)...");
        List<Hero> allHeroesFromDb = heroRepository.findAll();

        for (Hero hero : allHeroesFromDb) {
            heroIdMap.put(hero.getLocalizedName().toLowerCase(), hero.getId());
            heroNameByIdMap.put(hero.getId(), hero.getLocalizedName());
            heroRoleByIdMap.put(hero.getId(), hero.getRoles());
        }

        System.out.println("Успех! Героев в памяти: " + heroIdMap.size());
    }

    public List<OpenDotaHero> get_Heroes() {
        OpenDotaHero[] heroes = openDotaClient.fetchAllHeroes();

        if (heroes == null) {
            return List.of();
        }

        return Arrays.asList(heroes);
    }

    public List<CounterHeroDTO> getTopCounters(String heroName, String role) {
        List<CounterHeroDTO> counters = new ArrayList<>();

        Long heroId = heroIdMap.get(heroName.toLowerCase());

        if (heroId == null) {
            throw new RuntimeException("Герой " + heroName + " не найден в OpenDota!");
        }

        OpenDotaMatchup[] response = openDotaClient.fetchMatchups(heroId);

        if (response != null) {
            for (OpenDotaMatchup odData : response) {
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
                        odData.getGames_played()
                ));
            }
        }

        return counters.stream()
                .filter(dto -> role == null || dto.getRole().toLowerCase().contains(role.toLowerCase()))
                .sorted(Comparator.comparing(CounterHeroDTO::getWinrate).reversed())
                .toList();
    }
}