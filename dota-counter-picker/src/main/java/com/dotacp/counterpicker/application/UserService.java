package com.dotacp.counterpicker.application;

import com.dotacp.counterpicker.domain.AppUser;
import com.dotacp.counterpicker.infrastructure.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser registerTestUser() {
        AppUser user = new AppUser();
        user.setUsername("ShadowFind");
        user.setEmail("sf@gmail.com");

        return appUserRepository.save(user);
    }
}
