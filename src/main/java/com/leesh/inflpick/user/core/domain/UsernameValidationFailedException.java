package com.leesh.inflpick.user.core.domain;

import java.text.MessageFormat;

import static com.leesh.inflpick.user.core.domain.Nickname.MAX_LENGTH;
import static com.leesh.inflpick.user.core.domain.Nickname.MIN_LENGTH;

public class UsernameValidationFailedException extends IllegalArgumentException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("유저 이름은 최소 %d자 이상, %d자 이하로 입력해주세요.".formatted(MIN_LENGTH, MAX_LENGTH));

    public UsernameValidationFailedException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
