package com.dotacp.counterpicker.infrastructure;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

public class OpenDotaClient {
    public OpenDotaClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.opendota.com/api")
                .build();
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
                heroRoleByIdMap.put(hero.getId(), hero.getRoles());
            }
            System.out.println("Успех! Загружено героев в память: " + heroIdMap.size());
        }
    }

    public List<OpenDotaHero> get_Heroes() {
        OpenDotaHero[] heroes = restClient.get()
                .uri("/heroes")
                .retrieve()
                .body(OpenDotaHero[].class);

        if (heroes == null) {
            return List.of();
        }

        return Arrays.asList(heroes);
    }

}