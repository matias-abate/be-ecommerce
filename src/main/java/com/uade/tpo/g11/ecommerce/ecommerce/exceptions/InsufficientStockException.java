package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
