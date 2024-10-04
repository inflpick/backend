package com.leesh.inflpick.common.port.in.exception;

import java.text.MessageFormat;

public class InvalidPageNumberException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("페이지는 0 이상이어야 합니다.");

    public InvalidPageNumberException() {
        super(ERROR_MESSAGE_FORMAT.toPattern());
    }
}
