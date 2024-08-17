package com.solomon.solomon.shared.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.solomon.solomon.shared.exceptions.AppExpection;

import lombok.AllArgsConstructor;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @AllArgsConstructor
    private static class ErrorResponse {
        @SuppressWarnings("unused")
        public final String error;
        @SuppressWarnings("unused")
        public final String message;
    }

    @ExceptionHandler(AppExpection.class)
    private ResponseEntity<ErrorResponse> handler(AppExpection ex) {
        ErrorResponse errorResponse = new ErrorResponse("OCORREU UM PROBLEMA",
                ex.getMessage());
        System.err.println(ex);

        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }
}
