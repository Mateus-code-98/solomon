package com.solomon.solomon.modules.users.dtos;

import java.time.LocalDateTime;

public record AddressOutputDTO(String id, String cep, String street, String neighborhood, String city,
        String state, LocalDateTime created_at, LocalDateTime updated_at) {
}
