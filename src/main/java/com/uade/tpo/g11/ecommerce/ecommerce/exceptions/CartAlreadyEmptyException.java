package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

public class CartAlreadyEmptyException extends RuntimeException {
    public CartAlreadyEmptyException(String message) {
        super(message);
    }
}