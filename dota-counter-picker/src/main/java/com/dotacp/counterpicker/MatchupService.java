package com.dotacp.counterpicker;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchupService {

    // Это наш "виртуальный браузер" для походов в интернет
    private final RestClient restClient;

    public MatchupService() {
        // Инициализируем его при запуске приложения
        this.restClient = RestClient.create();
    }

    // Главный метод, который мы будем вызывать из Контроллера
    public List<CounterHeroDTO> getTopCounters(String heroName) {
        List<CounterHeroDTO> counters = new ArrayList<>();

        // 1. Позже здесь мы сформируем ссылку (URL) на сайт со статистикой для конкретного героя (heroName)

        // 2. Позже здесь мы заставим restClient сделать GET-запрос по этой ссылке

        // 3. Позже мы распарсим ответ и заполним наш список counters

        // Пока возвращаем заглушку, чтобы проверить, что всё работает
        counters.add(new CounterHeroDTO("Dummy Anti-Mage", "Carry", 55.5));

        return counters;
    }
}