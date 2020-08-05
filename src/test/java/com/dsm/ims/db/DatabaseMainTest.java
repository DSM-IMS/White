package com.dsm.ims.db;

import com.dsm.ims.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class DatabaseMainTest {
    @Autowired
    private static EntityManager em;

    public static void main(String[] args) {
        User user = new User();
        user.setId("aaa");
        user.setPw("bbb");
        user.setName("ccc");
        em.persist(user);

        System.out.println("execute");
    }
}
