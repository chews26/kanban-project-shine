package com.example.prello.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponseDto> CustomExceptionHandler(CustomException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getErrCode(), e.getMessage()), e.getHttpStatus());
    }
}
