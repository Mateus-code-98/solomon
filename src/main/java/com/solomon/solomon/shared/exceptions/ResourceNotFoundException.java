package com.solomon.solomon.shared.exceptions;

public class ResourceNotFoundException extends AppExpection {
    public ResourceNotFoundException(String resource) {
        super("(" + resource.toUpperCase() + ") não encontrado(a)", 404);
    }
}
