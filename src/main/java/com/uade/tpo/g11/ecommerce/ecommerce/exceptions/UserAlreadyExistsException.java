package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

import org.hibernate.HibernateException;

public class UserAlreadyExistsException extends HibernateException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}