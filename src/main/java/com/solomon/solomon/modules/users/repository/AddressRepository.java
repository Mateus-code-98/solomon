package com.solomon.solomon.modules.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solomon.solomon.modules.users.model.Address;

public interface AddressRepository extends JpaRepository<Address, String> {
}