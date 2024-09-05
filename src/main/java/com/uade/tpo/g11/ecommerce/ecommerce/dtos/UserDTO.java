package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String username;
    private String email;
    private LocalDate birth;
    private String firstname;
    private String lastname;
    private String role;

}
