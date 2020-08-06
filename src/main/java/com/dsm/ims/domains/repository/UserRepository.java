package com.dsm.ims.domains.repository;

import com.dsm.ims.domains.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepository {
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public User findById(String id) {
        return entityManager.find(User.class, id);
    }
}
