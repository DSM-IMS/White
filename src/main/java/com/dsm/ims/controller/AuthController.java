package com.dsm.ims.controller;

import com.dsm.ims.controller.form.UserForm;
import com.dsm.ims.domain.User;
import com.dsm.ims.service.AuthService;
import com.dsm.ims.service.JwtService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Autowired
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @GetMapping("/login")
    public String login(UserForm userForm) {
        if(userForm == null) throw new IllegalArgumentException();

        User user = new User();
        user.setId(userForm.getId());
        user.setPw(userForm.getPw());

        String json = authService.login(user);

        return json;
    }
}