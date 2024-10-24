package com.leesh.inflpick.v2.user.domain.exception;

public class InvalidNicknameFormatException extends RuntimeException {
    public InvalidNicknameFormatException(String message) {
        super(message);
    }
}
