package com.dotacp.counterpicker.controller;

import com.dotacp.counterpicker.application.UserService;
import com.dotacp.counterpicker.domain.AppUser;
import com.dotacp.counterpicker.infrastructure.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AppUserRepository appUserRepository;
    private UserInfoService service;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @GetMapping("/create_test_user")
    public AppUser createUser() {
        return userService.registerTestUser();
    }

    @GetMapping("/get_users")
    public List<AppUser> getUsers() { return appUserRepository.findAll();}

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
