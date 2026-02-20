package com.dotacp.counterpicker;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HeroService {
    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    public List<Hero> getHeroesByAttribute(String attribute) {
        return heroRepository.findAllByAttribute(attribute);
    }

    public Hero createHero(Hero hero) {
        String upperName = hero.getName().toUpperCase();
        hero.setName(upperName);

        return heroRepository.save(hero);
    }

    public Hero getHeroById(Long id) {
        return heroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Герой с ID " + id + " не найден!"
                ));
    }

    public Hero updateHero(Long id, Hero newHeroData) {
        Hero existingHero = getHeroById(id);

        if (newHeroData.getName() != null) {
            existingHero.setName(newHeroData.getName().toUpperCase());
        }

        if (newHeroData.getAttribute() != null) {
            existingHero.setAttribute(newHeroData.getAttribute());
        }

        if (newHeroData.getRole() != null) {
            existingHero.setRole(newHeroData.getRole());
        }

        return heroRepository.save(existingHero);
    }

    public void deleteHero(Long id) {
        Hero hero = getHeroById(id);
        heroRepository.delete(hero);
    }
}
