package com.leesh.inflpick.user.v2.port.in.exception;

public class DuplicatedSocialUserException extends RuntimeException {
    public DuplicatedSocialUserException(String message) {
        super(message);
    }
}
