package com.uade.tpo.g11.ecommerce.ecommerce.exceptions;

import org.hibernate.HibernateException;

public class GenericException extends HibernateException {
  private final Exception exception;

  public GenericException(String message, Exception e) {
    super(message);
    this.exception = e;
  }

  public String getMessage() {
    return exception.getMessage();
  }
}