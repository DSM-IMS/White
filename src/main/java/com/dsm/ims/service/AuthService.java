package com.dsm.ims.service;

import com.dsm.ims.domain.User;
import com.dsm.ims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(User user) {
        userRepository.findById(user.getId())
                .ifPresent(u -> {

                });
    }

}
