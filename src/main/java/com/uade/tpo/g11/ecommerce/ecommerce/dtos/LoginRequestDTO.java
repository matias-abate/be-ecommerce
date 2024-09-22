package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDTO {
    @NotNull @Email
    private String email;

    @NotNull
    private String password;
}
