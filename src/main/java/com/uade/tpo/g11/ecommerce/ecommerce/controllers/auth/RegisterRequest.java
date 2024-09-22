package com.uade.tpo.g11.ecommerce.ecommerce.controllers.auth;

import com.uade.tpo.g11.ecommerce.ecommerce.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private LocalDate birthDate;
    private String firstname;
    private String lastname;
    private RoleEntity role;
}
