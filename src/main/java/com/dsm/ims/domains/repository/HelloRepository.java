package com.dsm.ims.domains.repository;

import com.dsm.ims.domains.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class HelloRepository {
    private EntityManager entityManager;

    public HelloRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public User findById(String id) {
        System.out.println(entityManager);
        System.out.println(entityManager.find(User.class, id));
        return entityManager.find(User.class, id);
    }
}
