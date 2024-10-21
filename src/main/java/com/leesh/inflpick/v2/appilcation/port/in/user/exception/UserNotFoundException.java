package com.leesh.inflpick.v2.appilcation.port.in.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
