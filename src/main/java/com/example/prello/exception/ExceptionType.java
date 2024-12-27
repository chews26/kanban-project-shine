package com.example.prello.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionType {
    HttpStatus getHttpStatus();
    String getName();
    String getMessage();
}
