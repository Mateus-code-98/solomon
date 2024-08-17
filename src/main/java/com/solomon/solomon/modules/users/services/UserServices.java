package com.solomon.solomon.modules.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.solomon.solomon.modules.users.dtos.CreateUserInputDTO;
import com.solomon.solomon.modules.users.dtos.UpdateUserDTO;
import com.solomon.solomon.modules.users.dtos.UserOutputDTO;
import com.solomon.solomon.modules.users.model.User;
import com.solomon.solomon.modules.users.repository.CustomUserRepository;
import com.solomon.solomon.modules.users.repository.UserRepository;
import com.solomon.solomon.shared.dtos.ListInputDTO;
import com.solomon.solomon.shared.dtos.ListOutputDTO;
import com.solomon.solomon.shared.exceptions.ConflitException;
import com.solomon.solomon.shared.exceptions.ResourceNotFoundException;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CustomUserRepository customRepository;

    public UserOutputDTO create(CreateUserInputDTO CreateUserInputDTO) {
        User existentUser = repository.findByEmail(CreateUserInputDTO.email());
        if (existentUser != null)
            throw new ConflitException("Email");

        CreateUserInputDTO = this.createUserWithEncryptedPassword(CreateUserInputDTO);

        User user = new User(CreateUserInputDTO);

        repository.save(user);

        return user.outputUser();
    }

    public ListOutputDTO<UserOutputDTO> list(ListInputDTO inputData) {
        List<User> data = customRepository.list(inputData);
        Integer count = customRepository.count(inputData);

        List<UserOutputDTO> outputData = data.stream().map(User::outputUser).toList();

        ListOutputDTO<UserOutputDTO> response = new ListOutputDTO<UserOutputDTO>(count, outputData);

        return response;
    }

    public UserOutputDTO update(String id, UpdateUserDTO inputData) {
        String email = inputData.email();
        String name = inputData.name();
        String phone = inputData.phone();

        User user = repository.findById(id).get();
        if (user == null)
            throw new ResourceNotFoundException("Usuário(a)");

        User existentUser = repository.findByEmail(email);
        if (existentUser != null && !existentUser.getId().equals(id))
            throw new ConflitException("Email");

        user.setEmail(email);
        user.setName(name);
        user.setPhone(phone);

        repository.save(user);

        return user.outputUser();
    }

    public UserOutputDTO findById(String id) {
        User user = repository.findById(id).get();

        if (user == null)
            throw new ResourceNotFoundException("Usuário(a)");

        return user.outputUser();
    }

    private CreateUserInputDTO createUserWithEncryptedPassword(CreateUserInputDTO CreateUserInputDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(CreateUserInputDTO.password());

        return new CreateUserInputDTO(
                CreateUserInputDTO.name(),
                CreateUserInputDTO.email(),
                CreateUserInputDTO.phone(),
                encryptedPassword,
                CreateUserInputDTO.role());
    }

}
