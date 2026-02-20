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


}