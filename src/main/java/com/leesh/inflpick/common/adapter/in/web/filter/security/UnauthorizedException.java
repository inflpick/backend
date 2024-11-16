package com.leesh.inflpick.common.adapter.in.web.filter.security;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
