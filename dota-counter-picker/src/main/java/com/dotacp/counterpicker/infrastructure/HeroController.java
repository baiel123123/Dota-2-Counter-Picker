package com.dotacp.counterpicker.infrastructure;

import com.dotacp.counterpicker.domain.Hero;
import com.dotacp.counterpicker.application.CounterHeroDTO;
import com.dotacp.counterpicker.application.HeroService;
import com.dotacp.counterpicker.application.MatchupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/heroes")
public class HeroController {
        private final HeroService heroService;
        private final MatchupService matchupService;


        @GetMapping
        public List<Hero> getHeroes() {
                return heroService.getAllHeroes();
        }

        @GetMapping("/attribute/{attribute}")
        public List<Hero> getHeroesByAttribute(@PathVariable String attribute) {
                return heroService.getHeroesByAttribute(attribute);
        }

        @PostMapping
        public Hero createHero(@RequestBody Hero hero) {
                return heroService.createHero(hero);
        }

        @GetMapping("/{id}")
        public Hero getHeroById(@PathVariable Long id) {
                return heroService.getHeroById(id);
        }

        @PutMapping("/{id}")
        public Hero updateHero(@PathVariable Long id, @RequestBody Hero heroDetails) {
                return heroService.updateHero(id, heroDetails);
        }

        @GetMapping("/{name}/counters")
        public List<CounterHeroDTO> getCounters(@PathVariable String name) {
                return matchupService.getTopCounters(name);
        }
}