package com.dotacp.counterpicker.controller;

import com.dotacp.counterpicker.application.UserService;
import com.dotacp.counterpicker.domain.AppUser;
import com.dotacp.counterpicker.infrastructure.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AppUserRepository appUserRepository;

    @GetMapping("/create_test_user")
    public AppUser createUser() {
        return userService.registerTestUser();
    }

    @GetMapping("/get_users")
    public List<AppUser> getUsers() { return AppUserRepository.}
}
