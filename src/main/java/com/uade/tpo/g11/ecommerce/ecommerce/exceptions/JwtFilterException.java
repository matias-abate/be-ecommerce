package com.uade.beappsint.exception;

import org.hibernate.HibernateException;

public class JwtFilterException extends HibernateException {
  private static final long serialVersionUID = 1L;

  public JwtFilterException(String message) {
    super(message);
  }

  public JwtFilterException(String message, Throwable cause) {
    super(message, cause);
  }
}
