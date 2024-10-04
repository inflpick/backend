package com.leesh.inflpick.common.port.in.exception;

import java.text.MessageFormat;

public class InvalidPageSizeException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("페이지 크기는 1 이상, 100 이하로 입력해주세요.");

    public InvalidPageSizeException() {
        super(ERROR_MESSAGE_FORMAT.toPattern());
    }
}
