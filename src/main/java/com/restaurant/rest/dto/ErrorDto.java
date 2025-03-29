package com.restaurant.rest.dto;

import org.springframework.http.HttpStatus;

public record ErrorDto(HttpStatus code, String message) {
}
