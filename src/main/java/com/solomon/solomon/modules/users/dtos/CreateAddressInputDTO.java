package com.solomon.solomon.modules.users.dtos;

public record CreateAddressInputDTO(String street, String neighborhood, String city, String state, String cep) {
}