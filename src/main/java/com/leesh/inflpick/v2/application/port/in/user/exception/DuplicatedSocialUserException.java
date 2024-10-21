package com.leesh.inflpick.v2.application.port.in.user.exception;

public class DuplicatedSocialUserException extends RuntimeException {
    public DuplicatedSocialUserException(String message) {
        super(message);
    }
}
