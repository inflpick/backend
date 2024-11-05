package com.leesh.inflpick.v2.token.application.port.in.exception;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException(String token) {
        super("Invalid refresh token: " + token);
    }
}
