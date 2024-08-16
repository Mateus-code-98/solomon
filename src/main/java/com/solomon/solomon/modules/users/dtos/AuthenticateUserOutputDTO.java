package com.solomon.solomon.modules.users.dtos;

import com.solomon.solomon.modules.users.model.UserRole;

public record AuthenticateUserOutputDTO(String id, String token, String email, UserRole role, String name) {
}
