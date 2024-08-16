package com.solomon.solomon.modules.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solomon.solomon.modules.users.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}