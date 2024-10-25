package com.leesh.inflpick.v2.shared.adapter.in.web;

public class MissingRequiredFieldsException extends IllegalArgumentException {
    public MissingRequiredFieldsException(String message) {
        super(message);
    }
}
