package com.dotacp.counterpicker.application;

import com.dotacp.counterpicker.domain.AppUser;
import com.dotacp.counterpicker.domain.Hero;
import com.dotacp.counterpicker.exception.HeroNotFoundException;
import com.dotacp.counterpicker.exception.UserNotFoundException;
import com.dotacp.counterpicker.infrastructure.AppUserRepository;
import com.dotacp.counterpicker.infrastructure.HeroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final AppUserRepository appUserRepository;
    private final HeroRepository heroRepository;

    public List<AppUser> getUsers() { return appUserRepository.findAll(); }

    public AppUser registerTestUser() {
        AppUser user = new AppUser();
        user.setUsername("ShadowFind");
        user.setEmail("sf@gmail.com");

        return appUserRepository.save(user);
    }

    public AppUser addFavoriteHero(Long user_id, Long hero_id) {
        AppUser user = appUserRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException("Пользователь c id " + user_id + " не найден в БД!"));
        Hero hero = heroRepository.findById(hero_id).orElseThrow(() -> new HeroNotFoundException("Герой с айди" + hero_id + " не найден в БД!"));

        Set<Hero> user_fav = user.getFavoriteHeroes();
        user_fav.add(hero);

        return appUserRepository.save(user);
    }
}
