package com.solomon.course.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solomon.course.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public ResponseEntity<User[]> findAll() {
        User user1 = new User("Mateus", "teu-9@hotmail.com", "75999866581", "123456");

        User[] users = { user1 };

        return ResponseEntity.status(200).body(users);
    }
}
