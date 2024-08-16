package com.solomon.solomon.shared.exceptions;

public class ConflitException extends AppExpection {
    public ConflitException(String field) {
        super("(" + field.toUpperCase() + ") em conflito", 409);
    }

}
