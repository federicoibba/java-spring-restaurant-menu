package com.restaurant.rest.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
  public NotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
