package com.solomon.solomon.modules.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.solomon.solomon.modules.users.dtos.AuthenticateUserOutputDTO;
import com.solomon.solomon.modules.users.exceptions.CredentialsException;
import com.solomon.solomon.modules.users.model.User;
import com.solomon.solomon.modules.users.repository.UserRepository;
import com.solomon.solomon.shared.exceptions.AppExpection;
import com.solomon.solomon.shared.exceptions.ResourceNotFoundException;
import com.solomon.solomon.shared.helpers.PasswordHelper;

@Service
public class AuthServices {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHelper passwordHelper;

    public AuthenticateUserOutputDTO session(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new CredentialsException();

        var usernamePassword = new UsernamePasswordAuthenticationToken(email,
                password);

        System.out.println("\n\n\n\nusernamePassword-" + usernamePassword + "\n\n\n\n");

        // var auth = this.authenticationManager.authenticate(usernamePassword);

        // System.out.println("\n\n\n\nauth-" + auth + "\n\n\n\n");

        AuthenticateUserOutputDTO authenticateUserOutput = new AuthenticateUserOutputDTO(
                user.getId(),
                "token",
                user.getEmail(),
                user.getRole(),
                user.getName());

        return authenticateUserOutput;
    }
}
