package com.solomon.solomon.shared.exceptions;

public class ForbiddenException extends AppExpection {
    public ForbiddenException() {
        super("Acesso negado", 403);
    }

}
