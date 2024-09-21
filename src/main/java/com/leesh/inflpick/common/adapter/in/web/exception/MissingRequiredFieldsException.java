package com.leesh.inflpick.common.adapter.in.web.exception;

public class MissingRequiredFieldsException extends IllegalArgumentException {
    public MissingRequiredFieldsException(String message) {
        super(message);
    }
}
