package com.leesh.inflpick.keyword.core.domain;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.regex.Pattern;

public record KeywordName(@NotNull String name) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 30;
    public static final MessageFormat ERROR_MESSAGE_FORMAT;
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9가-힣\\s]{%d,%d}$".formatted(MIN_LENGTH, MAX_LENGTH));

    static {
        String pattern = "키워드 명은 최소 %d자 이상, %d자 이하인 한글, 영어, 숫자, 띄어쓰기만 입력해야 합니다. 입력 값: {0}".formatted(MIN_LENGTH, MAX_LENGTH);
        ERROR_MESSAGE_FORMAT = new MessageFormat(pattern);
    }

    public KeywordName {
        validateInput(name);
    }

    private void validateInput(@NotNull String name) throws KeywordNameValidationFailedException {
        if (!PATTERN.matcher(name).matches()) {
            throw new KeywordNameValidationFailedException(ERROR_MESSAGE_FORMAT.format(new Object[]{name}));
        }
    }
}
