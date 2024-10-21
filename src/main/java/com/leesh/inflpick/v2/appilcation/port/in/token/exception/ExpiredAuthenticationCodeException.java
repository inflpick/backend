package com.leesh.inflpick.v2.appilcation.port.in.token.exception;

public class ExpiredAuthenticationCodeException extends RuntimeException {
    public ExpiredAuthenticationCodeException(String message) {
        super(message);
    }
}
