package com.nasa.asteroid.nasaasteroidapi.controller;

import com.nasa.asteroid.nasaasteroidapi.model.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<String>> constraintViolation(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(
                BaseResponse.<String>builder().error(exception.getReason()).status(exception.getStatusCode().value()).build()
        );
    }
}
