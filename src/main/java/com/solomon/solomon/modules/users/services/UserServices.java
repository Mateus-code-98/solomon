package com.solomon.solomon.modules.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.solomon.solomon.modules.users.dtos.CreateAddressInputDTO;
import com.solomon.solomon.modules.users.dtos.CreateUserInputDTO;
import com.solomon.solomon.modules.users.dtos.UpdateUserDTO;
import com.solomon.solomon.modules.users.dtos.UserOutputDTO;
import com.solomon.solomon.modules.users.model.Address;
import com.solomon.solomon.modules.users.model.User;
import com.solomon.solomon.modules.users.repository.AddressRepository;
import com.solomon.solomon.modules.users.repository.CustomUserRepository;
import com.solomon.solomon.modules.users.repository.UserRepository;
import com.solomon.solomon.shared.dtos.CepResultDTO;
import com.solomon.solomon.shared.dtos.ListInputDTO;
import com.solomon.solomon.shared.dtos.ListOutputDTO;
import com.solomon.solomon.shared.exceptions.ConflitException;
import com.solomon.solomon.shared.exceptions.ResourceNotFoundException;
import com.solomon.solomon.shared.services.CepService;

import jakarta.transaction.Transactional;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    @Autowired
    private CustomUserRepository customRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public UserOutputDTO create(CreateUserInputDTO CreateUserInputDTO) {
        User existentUser = this.repository.findByEmail(CreateUserInputDTO.email());
        if (existentUser != null)
            throw new ConflitException("Email");

        CreateUserInputDTO = this.createUserWithEncryptedPassword(CreateUserInputDTO);

        User user = new User(CreateUserInputDTO);

        Address address = this.createAddress(CreateUserInputDTO.cep());

        user.setAddress(address);

        this.repository.save(user);

        return user.outputUser();
    }

    public ListOutputDTO<UserOutputDTO> list(ListInputDTO inputData) {
        List<User> data = customRepository.list(inputData);
        Integer count = customRepository.count(inputData);

        List<UserOutputDTO> outputData = data.stream().map(User::outputUser).toList();

        ListOutputDTO<UserOutputDTO> response = new ListOutputDTO<UserOutputDTO>(count, outputData);

        return response;
    }

    @Transactional
    public UserOutputDTO update(String id, UpdateUserDTO inputData) {
        System.out.println(id);
        String email = inputData.email();
        String name = inputData.name();
        String phone = inputData.phone();
        String cep = inputData.cep();

        User user = this.repository.findById(id).get();
        if (user == null)
            throw new ResourceNotFoundException("Usuário(a)");

        Address address = this.addressRepository.findById(user.getAddress().getId()).get();

        this.updateAddress(address, cep);

        User existentUser = this.repository.findByEmail(email);
        if (existentUser != null && !existentUser.getId().equals(id))
            throw new ConflitException("Email");

        user.setEmail(email);
        user.setName(name);
        user.setPhone(phone);

        this.repository.save(user);

        return user.outputUser();
    }

    public UserOutputDTO findById(String id) {
        User user = this.repository.findById(id).get();

        if (user == null)
            throw new ResourceNotFoundException("Usuário(a)");

        return user.outputUser();
    }

    @Transactional
    private Address createAddress(String cep) {
        CreateAddressInputDTO addressInput = this.findCepDetails(cep);

        Address address = new Address(addressInput);

        this.addressRepository.save(address);

        return address;
    }

    @Transactional
    private Address updateAddress(Address address, String cep) {
        CreateAddressInputDTO addressInput = this.findCepDetails(cep);

        address.setNeighborhood(addressInput.neighborhood());
        address.setStreet(addressInput.street());
        address.setCity(addressInput.city());
        address.setState(addressInput.state());
        address.setCep(addressInput.cep());

        this.addressRepository.save(address);

        return address;
    }

    private CreateAddressInputDTO findCepDetails(String cep) {
        CepResultDTO cepResult = this.cepService.getCepDetails(cep);

        return new CreateAddressInputDTO(cepResult.logradouro(), cepResult.bairro(), cepResult.localidade(),
                cepResult.uf(), cep);
    }

    private CreateUserInputDTO createUserWithEncryptedPassword(CreateUserInputDTO CreateUserInputDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(CreateUserInputDTO.password());

        return new CreateUserInputDTO(
                CreateUserInputDTO.name(),
                CreateUserInputDTO.email(),
                CreateUserInputDTO.phone(),
                encryptedPassword,
                CreateUserInputDTO.cep(),
                CreateUserInputDTO.role());
    }
}
