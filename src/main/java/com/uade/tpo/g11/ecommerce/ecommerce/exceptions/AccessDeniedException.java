package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

import org.hibernate.HibernateException;

public class AccessDeniedException extends HibernateException {
    public AccessDeniedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}