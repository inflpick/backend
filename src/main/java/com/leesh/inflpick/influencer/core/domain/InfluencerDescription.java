package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public record InfluencerDescription(@NotNull String description) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 50000;
    private static final MessageFormat ERROR_MESSAGE_FORMAT;

    static {
        String pattern = "인플루언서 설명은 최사 %d자 이상, %d자 이하로 입력해야 합니다. 입력 값: {0}".formatted(MIN_LENGTH, MAX_LENGTH);
        ERROR_MESSAGE_FORMAT = new MessageFormat(pattern);
    }

    public InfluencerDescription {
        validateInput(description);
    }

    private static void validateInput(@NotNull String description) throws ConstraintViolationException {
        if (description.isBlank() || description.isEmpty() || description.length() > MAX_LENGTH) {
            throw new ConstraintViolationException(ERROR_MESSAGE_FORMAT.toPattern());
        }
    }
}
