package com.leesh.inflpick.v2.user.application.port.in.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
