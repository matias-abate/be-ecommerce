package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException(String message) {
        super(message);
    }
}