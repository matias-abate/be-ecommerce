package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {

    private String message;
    private HttpStatus status;

    public ErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
