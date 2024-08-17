package com.solomon.solomon.modules.users.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.solomon.solomon.modules.users.dtos.AddressOutputDTO;
import com.solomon.solomon.modules.users.dtos.CreateAddressInputDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "address")
@Entity(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String cep;

    private String street;

    private String neighborhood;

    private String city;

    private String state;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address(CreateAddressInputDTO data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.city = data.city();
        this.state = data.state();
        this.cep = data.cep();
    }

    public AddressOutputDTO toOutputDTO() {
        return new AddressOutputDTO(this.id, this.cep, this.street, this.neighborhood, this.city, this.state,
                this.created_at, this.updated_at);
    }
}