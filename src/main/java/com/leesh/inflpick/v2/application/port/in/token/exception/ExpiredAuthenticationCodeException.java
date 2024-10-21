package com.leesh.inflpick.v2.application.port.in.token.exception;

public class ExpiredAuthenticationCodeException extends RuntimeException {
    public ExpiredAuthenticationCodeException(String message) {
        super(message);
    }
}
