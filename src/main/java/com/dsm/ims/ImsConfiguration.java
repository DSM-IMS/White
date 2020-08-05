package com.dsm.ims;

import com.dsm.ims.repository.JpaUserRepository;
import com.dsm.ims.repository.UserRepository;
import com.dsm.ims.service.AuthService;
import com.dsm.ims.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class ImsConfiguration {

    private EntityManager entityManager;

    @Autowired
    public ImsConfiguration(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(entityManager);
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }

    @Bean
    public AuthService authService() {
        return new AuthService(userRepository(), jwtService());
    }
}
