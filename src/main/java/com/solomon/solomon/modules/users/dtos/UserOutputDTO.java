package com.solomon.solomon.modules.users.dtos;

import java.time.LocalDateTime;

import com.solomon.solomon.modules.users.model.UserRole;

public record UserOutputDTO(
                String id,
                String name,
                String email,
                String phone,
                UserRole role,
                AddressOutputDTO address,
                LocalDateTime created_at,
                LocalDateTime updated_at) {
}