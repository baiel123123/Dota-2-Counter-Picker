package com.dotacp.counterpicker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {
        private final HeroRepository heroRepository;

        public HeroController(HeroRepository heroRepository) {
                this.heroRepository = heroRepository;
        }
        @GetMapping
        public List<Hero> getHeroes() {
                return heroRepository.findAll(); 
        }
}