package com.solomon.solomon.modules.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.solomon.solomon.modules.users.dtos.AuthenticateUserOutputDTO;
import com.solomon.solomon.modules.users.model.User;
import com.solomon.solomon.modules.users.repository.UserRepository;
import com.solomon.solomon.shared.infra.security.TokenService;

@Service
public class AuthServices {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public AuthenticateUserOutputDTO session(String email, String password) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(email,
                password);

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        User user = userRepository.findByEmail(email);

        AuthenticateUserOutputDTO authenticateUserOutput = new AuthenticateUserOutputDTO(
                user.getId(),
                token,
                user.getEmail(),
                user.getRole(),
                user.getName());

        return authenticateUserOutput;
    }
}
