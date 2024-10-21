package com.leesh.inflpick.v2.application.port.in.token.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
