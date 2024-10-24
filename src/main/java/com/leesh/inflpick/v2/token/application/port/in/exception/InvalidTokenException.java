package com.leesh.inflpick.v2.token.application.port.in.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
