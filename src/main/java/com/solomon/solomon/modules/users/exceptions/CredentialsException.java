package com.solomon.solomon.modules.users.exceptions;

import com.solomon.solomon.shared.exceptions.AppExpection;

public class CredentialsException extends AppExpection {
    public CredentialsException() {
        super("Credenciais inválidas", 401);
    }
}
