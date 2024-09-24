package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message) {
        super(message);
    }
}
