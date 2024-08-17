package com.solomon.solomon.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AppExpection {
    public ResourceNotFoundException(String resource) {
        super("(" + resource.toUpperCase() + ") não encontrado(a)", HttpStatus.NOT_FOUND.value());
    }
}
