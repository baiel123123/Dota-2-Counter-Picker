package com.dotacp.counterpicker.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
public class OpenDotaClient {
    private final RestClient restClient;

    public OpenDotaClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.opendota.com/api")
                .build();
    }


    public OpenDotaHero[] fetchAllHeroes() {
        return restClient.get()
                .uri("/heroes")
                .retrieve()
                .body(OpenDotaHero[].class);
    }

    public OpenDotaMatchup[] fetchMatchups(Long heroId) {
        return restClient.get()
                .uri("/heroes/" + heroId + "/matchups")
                .retrieve()
                .body(OpenDotaMatchup[].class);
    }
}