package com.leesh.inflpick.v2.token.adapter.in.web.exception;

public class NotSupportedGrantTypeException extends RuntimeException {
    public NotSupportedGrantTypeException(String grantType) {
        super("Not supported grant type, grantType: " + grantType);
    }
}
