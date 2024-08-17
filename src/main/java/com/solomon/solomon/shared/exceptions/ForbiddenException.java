package com.solomon.solomon.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends AppExpection {
    public ForbiddenException() {
        super("Acesso negado", HttpStatus.FORBIDDEN.value());
    }

}
