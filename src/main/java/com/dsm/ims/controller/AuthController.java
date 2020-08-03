package com.dsm.ims.controller;

import com.dsm.ims.domain.User;
import com.dsm.ims.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/auth")
    public String signIn(User user) {
        authService.login(user);

        return "test";
    }
}