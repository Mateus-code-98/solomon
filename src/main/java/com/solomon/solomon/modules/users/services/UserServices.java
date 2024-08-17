package com.solomon.solomon.modules.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.solomon.solomon.modules.users.dtos.CreateUserDTO;
import com.solomon.solomon.modules.users.model.User;
import com.solomon.solomon.modules.users.repository.UserRepository;
import com.solomon.solomon.shared.exceptions.ConflitException;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    public User create(CreateUserDTO createUserDTO) {
        User existentUser = repository.findByEmail(createUserDTO.email());
        if (existentUser != null)
            throw new ConflitException("Email");

        String encryptedPassword = new BCryptPasswordEncoder().encode(createUserDTO.password());

        createUserDTO = new CreateUserDTO(
                createUserDTO.name(),
                createUserDTO.email(),
                createUserDTO.phone(),
                encryptedPassword,
                createUserDTO.role());

        User user = new User(createUserDTO);

        repository.save(user);

        return user;
    }

    public User update(String id, CreateUserDTO createUserDTO) {
        User user = repository.findById(id).get();

        repository.save(user);

        return user;
    }

}
