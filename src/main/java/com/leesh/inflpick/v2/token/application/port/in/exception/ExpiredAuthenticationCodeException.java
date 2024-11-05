package com.leesh.inflpick.v2.token.application.port.in.exception;

public class ExpiredAuthenticationCodeException extends RuntimeException {
    public ExpiredAuthenticationCodeException() {
        super("Expired authentication code");
    }
}
