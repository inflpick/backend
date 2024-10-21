package com.leesh.inflpick.v2.configuration.security;

import org.springframework.security.core.AuthenticationException;

public class ExpiredAuthenticationException extends AuthenticationException {
    public ExpiredAuthenticationException(String message) {
        super(message);
    }
}
