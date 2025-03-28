package com.restaurant.rest.exception;

import org.springframework.http.HttpStatus;

public record ErrorDto(HttpStatus code, String message) {
}
