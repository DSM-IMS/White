package com.dsm.ims.domains.service;

import com.dsm.ims.domains.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Autowired AuthService authService;

    @Test
    void login() {
        User user = new User();
        user.setId("aaa");
        user.setPw("bbb");
    }

    @Test
    void accessTokenReissuance() {
    }

    @Test
    void join() {
    }
}