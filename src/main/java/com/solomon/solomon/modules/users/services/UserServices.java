package com.solomon.solomon.modules.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solomon.solomon.modules.users.dtos.CreateUserDTO;
import com.solomon.solomon.modules.users.model.User;
import com.solomon.solomon.modules.users.repository.UserRepository;
import com.solomon.solomon.shared.helpers.PasswordHelper;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordHelper passwordHelper;

    public User create(CreateUserDTO createUserDTO) {

        createUserDTO = new CreateUserDTO(
                createUserDTO.name(),
                createUserDTO.email(),
                createUserDTO.phone(),
                passwordHelper.hashPassword(createUserDTO.password()),
                createUserDTO.role());

        User user = new User(createUserDTO);

        System.out.println("\n\n\n\nuser-" + user + "\n\n\n\n");

        repository.save(user);

        return user;
    }

    public User update(String id, CreateUserDTO createUserDTO) {
        User user = repository.findById(id).get();

        repository.save(user);

        return user;
    }

}
