package com.example.prello.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errCode;
    private final String message;

    public CustomException(ExceptionType exception) {
        super();
        this.httpStatus = exception.getHttpStatus();
        this.errCode = exception.getName();
        this.message = exception.getMessage();

    }
}
