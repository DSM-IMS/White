package com.dsm.ims.controller;

import com.dsm.ims.domains.domain.User;
import com.dsm.ims.domains.service.AuthService;
import com.dsm.ims.form.RefreshTokenForm;
import com.dsm.ims.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login(UserForm userForm) {
        User user = new User();
        user.setId(userForm.getId());
        user.setPw(userForm.getPw());

        return authService.login(user);
    }

    @GetMapping("/refresh")
    public String refreshToken(RefreshTokenForm refreshTokenForm) {
        String refreshToken = refreshTokenForm.getRefreshToken();



        return null;
    }

    @GetMapping("/join")
    public void join(User user) {
        authService.join(user);
    }
}