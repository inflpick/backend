package com.leesh.inflpick.user.domain.exception;

public class InvalidNicknameFormatException extends RuntimeException {
    public InvalidNicknameFormatException(String message) {
        super(message);
    }
}
