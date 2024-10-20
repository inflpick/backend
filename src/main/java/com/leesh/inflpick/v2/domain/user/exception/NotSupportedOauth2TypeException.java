package com.leesh.inflpick.v2.domain.user.exception;

public class NotSupportedOauth2TypeException extends RuntimeException {
    public NotSupportedOauth2TypeException(String message) {
        super(message);
    }
}
