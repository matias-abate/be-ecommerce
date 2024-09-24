package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

