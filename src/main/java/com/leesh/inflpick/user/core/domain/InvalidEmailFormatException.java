package com.leesh.inflpick.user.core.domain;

import java.text.MessageFormat;

public class InvalidEmailFormatException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("올바른 이메일 형식을 입력해주세요.");

    public InvalidEmailFormatException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
