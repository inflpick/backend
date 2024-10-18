package com.leesh.inflpick.user.port.in;

public class InvalidAuthenticationCodeException extends RuntimeException {
    public InvalidAuthenticationCodeException(String message) {
        super(message);
    }
}
