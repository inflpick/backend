package com.leesh.inflpick.v2.configuration.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidAuthenticationException extends AuthenticationException {
    public InvalidAuthenticationException(String message) {
        super(message);
    }
}
