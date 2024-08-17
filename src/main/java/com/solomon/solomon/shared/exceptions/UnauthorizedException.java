package com.solomon.solomon.shared.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AppExpection {
    public UnauthorizedException() {
        super("Credenciaisaaaa inválidas", HttpStatus.UNAUTHORIZED.value());
    }
}
