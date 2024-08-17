package com.solomon.solomon.shared.exceptions;

public class UnauthorizedException extends AppExpection {
    public UnauthorizedException() {
        super("Credenciaisaaaa inválidas", 401);
    }
}
