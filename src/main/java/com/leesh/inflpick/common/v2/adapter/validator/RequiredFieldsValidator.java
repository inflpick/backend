package com.leesh.inflpick.common.v2.adapter.validator;

import com.leesh.inflpick.common.adapter.in.web.exception.MissingRequiredFieldsException;

public class RequiredFieldsValidator {

    private RequiredFieldsValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validate(Object... object) {
        for (Object o : object) {
            if (o == null) {
                throw new MissingRequiredFieldsException("필수 입력값을 입력해주세요.");
            }
            if (o instanceof String && ((String) o).isBlank()) {
                throw new MissingRequiredFieldsException("필수 입력값을 입력해주세요.");
            }
        }
    }
}
