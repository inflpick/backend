package com.leesh.inflpick.user.port;

import java.text.MessageFormat;

public class InvalidUserSortTypeException extends RuntimeException {

    public static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("정렬 타입은 %s 중 하나여야 합니다.".formatted(String.join(", ", UserSortType.availableSortTypes())));

    public InvalidUserSortTypeException(String input) {
        super(ERROR_MESSAGE_FORMAT.toPattern() + input);
    }
}
