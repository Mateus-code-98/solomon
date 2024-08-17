package com.solomon.solomon.modules.users.dtos;

import com.solomon.solomon.modules.users.model.UserRole;

public record UserOutputDTO(
        String id,
        String name,
        String email,
        String phone,
        UserRole role) {
}