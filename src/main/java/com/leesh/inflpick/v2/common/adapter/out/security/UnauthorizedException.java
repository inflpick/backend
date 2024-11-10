package com.leesh.inflpick.v2.common.adapter.out.security;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
