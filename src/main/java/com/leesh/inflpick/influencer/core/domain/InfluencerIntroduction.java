package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public record InfluencerIntroduction(@NotNull String introduction) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 300;
    public static final MessageFormat ERROR_MESSAGE_FORMAT;

    static {
        String pattern = "인플루언서 소개는 최소 %d자 이상, %d자 이하로 입력해야 합니다. 입력 값: {0}".formatted(MIN_LENGTH, MAX_LENGTH);
        ERROR_MESSAGE_FORMAT = new MessageFormat(pattern);
    }

    public InfluencerIntroduction {
        validateInput(introduction);
    }

    private static void validateInput(@NotNull String description) throws CustomConstraintViolationExceptionBase {
        if (description.isBlank() || description.isEmpty() || description.length() > MAX_LENGTH) {
            throw new InfluencerIntroductionValidationFailedException(ERROR_MESSAGE_FORMAT.format(new Object[]{description}));
        }
    }

}
