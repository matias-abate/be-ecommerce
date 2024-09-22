package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

public class ProductAlreadyInWishListException extends RuntimeException {
    public ProductAlreadyInWishListException(String message) {
        super(message);
    }
}
