package com.uxpsystems.assignment.config;

public class RequestNotValidException extends RuntimeException {
    public RequestNotValidException() {
        super();
    }

    public RequestNotValidException(String message) {
        super(message);
    }
}
