package com.solomon.solomon.modules.users.dtos;

import com.solomon.solomon.modules.users.model.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phone,
        @NotNull UserRole role) {
}