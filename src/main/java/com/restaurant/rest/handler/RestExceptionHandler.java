package com.restaurant.rest.handler;

import com.restaurant.rest.dto.ErrorDto;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice(basePackages = "com.restaurant.rest")
public class RestExceptionHandler {

  @Hidden
  @ExceptionHandler({BadRequestException.class})
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorDto> handleBadRequestException(final BadRequestException exception) {
    log.warn("BadRequestException occur:", exception);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(HttpStatus.BAD_REQUEST, exception.getMessage()));
  }

  @Hidden
  @ExceptionHandler({NotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorDto> handleNotFoundException(final NotFoundException exception) {
    log.warn("NotFoundException occur:", exception);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(HttpStatus.NOT_FOUND, exception.getMessage()));
  }

  @Hidden
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorDto> handleValidationExceptions(final MethodArgumentNotValidException exception){
    log.warn("MethodArgumentNotValidException occur:", exception);
    List<String> errors = exception.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(HttpStatus.BAD_REQUEST, String.join(", ", errors)));
  }

}
