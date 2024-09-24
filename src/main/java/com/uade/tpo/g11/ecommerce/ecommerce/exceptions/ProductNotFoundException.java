package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
