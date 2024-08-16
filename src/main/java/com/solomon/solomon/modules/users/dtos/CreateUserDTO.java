package com.solomon.solomon.modules.users.dtos;

import com.solomon.solomon.modules.users.model.UserRole;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
                @NotBlank String name,
                @NotBlank String email,
                @NotBlank String phone,
                @NotBlank String password,
                @NotBlank UserRole role) {
}