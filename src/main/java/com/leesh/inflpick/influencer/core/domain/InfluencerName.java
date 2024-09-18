package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public record InfluencerName(@NotNull String name) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 300;
    public static final MessageFormat ERROR_MESSAGE_FORMAT;
    public static final String ERROR_MESSAGE = "인플루언서 이름은 최소 %d자 이상, %d자 이하로 입력해야 합니다. 입력 값: {0}".formatted(MIN_LENGTH, MAX_LENGTH);

    static {
        ERROR_MESSAGE_FORMAT = new MessageFormat(ERROR_MESSAGE);
    }

    public InfluencerName {
        validateInput(name);
    }

    private void validateInput(@NotNull String name) throws InfluencerNameValidationFailedException {
        if (name.isBlank() || name.isEmpty() || name.length() > MAX_LENGTH) {
            throw new InfluencerNameValidationFailedException(ERROR_MESSAGE_FORMAT.format(new Object[]{name}));
        }
    }
}
