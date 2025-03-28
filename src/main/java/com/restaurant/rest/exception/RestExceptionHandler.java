package com.restaurant.rest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.restaurant.rest")
public class RestExceptionHandler {

  @ExceptionHandler({BadRequestException.class})
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorDto> handleBadRequestException(final BadRequestException exception) {
    log.warn("BadRequestException occur:", exception);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(HttpStatus.BAD_REQUEST, exception.getMessage()));
  }

  @ExceptionHandler({NotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorDto> handleNotFoundException(final NotFoundException exception) {
    log.warn("NotFoundException occur:", exception);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(HttpStatus.NOT_FOUND, exception.getMessage()));
  }
}
