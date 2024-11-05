package com.leesh.inflpick.v2.token.application.port.in.exception;

public class ExpiredRefreshTokenException extends RuntimeException {
    public ExpiredRefreshTokenException() {
        super("Expired refresh token");
    }
}
