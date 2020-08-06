package com.dsm.ims.repository;

import com.dsm.ims.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaUserRepository implements UserRepository {

    private EntityManager entityManager;

    @Autowired
    public JpaUserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public Optional<User> findByUid(int uid) {
        return Optional.ofNullable(entityManager.find(User.class, uid));
    }

    @Override
    public Optional<User> findById(String id) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findAny();
    }

}
