package com.leesh.inflpick.user.domain.exception;

public class NotSupportedOauth2TypeException extends RuntimeException {
    public NotSupportedOauth2TypeException(String oauth2Provider) {
        super("Not supported oauth2 provider: " + oauth2Provider);
    }
}
