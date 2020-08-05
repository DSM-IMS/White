package com.dsm.ims.repository;

import com.dsm.ims.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUid(int uid);
    Optional<User> findById(String id);
}