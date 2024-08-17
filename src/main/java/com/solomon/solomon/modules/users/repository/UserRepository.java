package com.solomon.solomon.modules.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solomon.solomon.modules.users.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    @SuppressWarnings("null")
    Optional<User> findById(String id);
}