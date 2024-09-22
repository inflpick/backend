package com.leesh.inflpick.keyword.core.domain;

import java.text.MessageFormat;

import static com.leesh.inflpick.keyword.core.domain.KeywordName.MAX_LENGTH;
import static com.leesh.inflpick.keyword.core.domain.KeywordName.MIN_LENGTH;

public class KeywordNameValidationFailedException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("키워드 명은 최소 %d자 이상, %d자 이하인 한글, 영어, 숫자, 띄어쓰기만 입력해야 합니다.".formatted(MIN_LENGTH, MAX_LENGTH));

    public KeywordNameValidationFailedException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
