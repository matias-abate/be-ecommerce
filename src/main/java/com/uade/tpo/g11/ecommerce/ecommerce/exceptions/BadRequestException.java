package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

import org.hibernate.HibernateException;

public class BadRequestException extends HibernateException {
  public BadRequestException(String message) {
    super(message);
  }

  public String getMessage() {
    return super.getMessage();
  }
}