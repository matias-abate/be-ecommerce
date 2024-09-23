package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserInfoDTO {
    private Integer id;

    // Basic Info
    private String email;
    private String  firstname;
    private String  lastname;
    private LocalDate dateOfBirth;

}
