package com.solomon.solomon.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solomon.solomon.modules.users.dtos.CreateUserInputDTO;
import com.solomon.solomon.modules.users.dtos.UpdateUserDTO;
import com.solomon.solomon.modules.users.dtos.UserOutputDTO;
import com.solomon.solomon.modules.users.services.UserServices;
import com.solomon.solomon.shared.dtos.ListInputDTO;
import com.solomon.solomon.shared.dtos.ListOutputDTO;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices service;

    @GetMapping
    public ResponseEntity<ListOutputDTO<UserOutputDTO>> list(
            @RequestBody @Valid ListInputDTO data) {

        ListOutputDTO<UserOutputDTO> response = service.list(data);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<UserOutputDTO> create(
            @RequestBody @Valid CreateUserInputDTO data) {

        UserOutputDTO user = service.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> findById(@PathVariable String id) {
        UserOutputDTO user = service.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> update(@PathVariable String id, @RequestBody UpdateUserDTO entity) {
        UserOutputDTO user = service.update(id, entity);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
