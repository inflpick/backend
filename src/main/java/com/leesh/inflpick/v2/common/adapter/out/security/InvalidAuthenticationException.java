package com.leesh.inflpick.v2.common.adapter.out.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidAuthenticationException extends AuthenticationException {
    public InvalidAuthenticationException() {
        super("Invalid accessToken");
    }
}
