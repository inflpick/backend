package com.leesh.inflpick.common.port.in.exception;

import com.leesh.inflpick.common.port.SortDirection;

import java.text.MessageFormat;

public class InvalidDirectionException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("정렬 방향은 %s 중 하나여야 합니다.".formatted(String.join(", ", SortDirection.availableDirectionsValues())));

    public InvalidDirectionException(String message) {
        super(message);
    }
}
