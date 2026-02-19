package com.dotacp.counterpicker;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {
        private final HeroService heroService;

        public HeroController(HeroService heroService) {
                this.heroService = heroService;
        }

        @GetMapping
        public List<Hero> getHeroes() {
                return heroService.getAllHeroes();
        }

        @PostMapping
        public Hero createHero(@RequestBody Hero hero) {
                return heroService.createHero(hero);
        }

        @GetMapping
        public Hero getHeroById(Long id) {
                return heroService.getHeroById(id);
        }

        public Hero updateHero(Long id) {

        }
}