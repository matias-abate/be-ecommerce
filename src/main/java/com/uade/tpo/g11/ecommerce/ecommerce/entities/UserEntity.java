package com.uade.tpo.g11.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    private String username;
    private String password;
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String firstname;
    private String lastname;

    // TODO: @Enumerated(EnumType.STRING)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;
}
