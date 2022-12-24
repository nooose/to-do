package com.noose.todo.exception;

import org.springframework.http.HttpStatus;

public class TodoException extends RuntimeException {

    private final HttpStatus httpStatus;

    public TodoException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
