package com.dsm.ims.domains.service;

import com.dsm.ims.domains.domain.User;
import com.dsm.ims.domains.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HelloService {
    private HelloRepository helloRepository;

    @Autowired
    public HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public User useJpa(User user) {
        return helloRepository.findById(user.getId());
    }

    public void save(User user) {
        helloRepository.save(user);
    }
}