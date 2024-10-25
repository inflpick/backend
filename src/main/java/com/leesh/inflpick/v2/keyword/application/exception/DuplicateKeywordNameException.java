package com.leesh.inflpick.v2.keyword.application.exception;

public class DuplicateKeywordNameException extends IllegalArgumentException {
    public DuplicateKeywordNameException(String message) {
        super(message);
    }
}
