package com.leesh.inflpick.token.application.port.in.exception;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException(String token) {
        super("Invalid refresh token: " + token);
    }
}
