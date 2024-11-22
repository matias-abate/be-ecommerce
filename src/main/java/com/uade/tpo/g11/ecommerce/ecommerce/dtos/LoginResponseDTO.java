package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String token;
    private int id;
    private Long expiresIn;
}
