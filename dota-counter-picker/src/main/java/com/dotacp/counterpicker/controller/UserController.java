package com.dotacp.counterpicker.controller;

import com.dotacp.counterpicker.application.UserService;
import com.dotacp.counterpicker.domain.AppUser;
import com.dotacp.counterpicker.infrastructure.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AppUserRepository appUserRepository;

    @GetMapping
    public AppUser create_user() {
        return userService.registerTestUser();
    }
}
