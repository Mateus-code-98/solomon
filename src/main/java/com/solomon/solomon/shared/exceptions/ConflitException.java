package com.solomon.solomon.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ConflitException extends AppExpection {
    public ConflitException(String field) {
        super("(" + field.toUpperCase() + ") em conflito", HttpStatus.CONFLICT.value());
    }

}
