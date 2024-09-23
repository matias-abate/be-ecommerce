package com.uade.tpo.g11.ecommerce.ecommerce.dtos;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponseDTO {
    private String message;
    private Object extra;
}