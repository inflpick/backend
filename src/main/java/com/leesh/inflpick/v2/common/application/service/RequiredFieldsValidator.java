package com.leesh.inflpick.v2.common.application.service;


import com.leesh.inflpick.v2.common.adapter.in.web.exception.MissingRequiredFieldsException;

public class RequiredFieldsValidator {

    private RequiredFieldsValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validate(Object... object) {
        for (Object o : object) {
            if (o == null) {
                throw new MissingRequiredFieldsException(null);
            }
            if (o instanceof String && ((String) o).isBlank()) {
                throw new MissingRequiredFieldsException(o);
            }
        }
    }
}
