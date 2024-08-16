package com.solomon.solomon.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solomon.solomon.modules.users.dtos.CreateUserDTO;
import com.solomon.solomon.modules.users.model.User;
import com.solomon.solomon.modules.users.model.UserRole;
import com.solomon.solomon.modules.users.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices service;

    @GetMapping
    public ResponseEntity<com.solomon.solomon.modules.users.model.User> findAll(
            @RequestParam(name = "role", required = true) @Valid UserRole role) {

        CreateUserDTO createUserDTO = new CreateUserDTO("Mateus", "teu-9@hotmail.com", "75999866581", "123456",
                UserRole.ADMIN);

        User user = service.create(createUserDTO);

        return ResponseEntity.status(200).body(user);
    }
}
