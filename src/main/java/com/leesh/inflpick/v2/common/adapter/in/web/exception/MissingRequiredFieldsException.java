package com.leesh.inflpick.v2.common.adapter.in.web.exception;

public class MissingRequiredFieldsException extends IllegalArgumentException {
    public MissingRequiredFieldsException(Object o) {
        super("missing required fields: " + o);
    }
}
