package com.dsm.ims.controller;

import com.dsm.ims.domains.domain.User;
import com.dsm.ims.domains.service.AuthService;
import com.dsm.ims.form.AccessTokenReissuanceResultForm;
import com.dsm.ims.form.LoginResultForm;
import com.dsm.ims.form.RefreshTokenForm;
import com.dsm.ims.form.UserForm;
import io.swagger.annotations.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Auth Controller")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "LOGIN", notes = "JWT 토큰 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Login Success"),
            @ApiResponse(code = 404, message = "Not User"),
            @ApiResponse(code = 500, message = "ID Mismatch OR Password Mismatch")
    })
    @GetMapping("/login")
    public LoginResultForm login(UserForm userForm) {
        User user = new User();
        user.setId(userForm.getId());
        user.setPw(userForm.getPw());

        return authService.login(user);
    }

    @ApiOperation(value = "REFRESH", notes = "ACCESS 토큰 재발급")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return Success"),
            @ApiResponse(code = 500, message = "Refresh Token Mismatch")
    })
    @GetMapping("/refresh")
    public AccessTokenReissuanceResultForm refreshToken(RefreshTokenForm refreshTokenForm) {
        String refreshToken = refreshTokenForm.getRefreshToken();

        return authService.accessTokenReissuance(refreshToken);
    }

    @GetMapping("/join")
    public void join(User user) {
        authService.join(user);
    }
}