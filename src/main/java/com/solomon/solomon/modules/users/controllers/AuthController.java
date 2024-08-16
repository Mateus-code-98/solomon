package com.solomon.solomon.modules.users.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solomon.solomon.modules.users.dtos.AuthenticateUserInputDTO;
import com.solomon.solomon.modules.users.dtos.AuthenticateUserOutputDTO;
import com.solomon.solomon.modules.users.services.AuthServices;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServices service;

    @PostMapping
    public ResponseEntity<AuthenticateUserOutputDTO> session(@RequestBody() @Valid AuthenticateUserInputDTO body) {
        AuthenticateUserOutputDTO authenticateUserOutput = service.session(body.email(), body.password());

        return ResponseEntity.status(200).body(authenticateUserOutput);
    }

}
