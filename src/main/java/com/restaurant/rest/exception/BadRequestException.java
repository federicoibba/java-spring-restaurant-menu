package com.restaurant.rest.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException{
  public BadRequestException() {
    super(HttpStatus.BAD_REQUEST);
  }

  public BadRequestException(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }}
