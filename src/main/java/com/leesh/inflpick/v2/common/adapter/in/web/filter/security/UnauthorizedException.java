package com.leesh.inflpick.v2.common.adapter.in.web.filter.security;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
