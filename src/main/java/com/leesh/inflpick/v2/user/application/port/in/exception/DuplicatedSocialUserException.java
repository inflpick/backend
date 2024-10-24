package com.leesh.inflpick.v2.user.application.port.in.exception;

public class DuplicatedSocialUserException extends RuntimeException {
    public DuplicatedSocialUserException(String message) {
        super(message);
    }
}
