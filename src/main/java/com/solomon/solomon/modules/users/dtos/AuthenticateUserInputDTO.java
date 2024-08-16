package com.solomon.solomon.modules.users.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthenticateUserInputDTO(@NotBlank String email, @NotBlank String password) {
}
