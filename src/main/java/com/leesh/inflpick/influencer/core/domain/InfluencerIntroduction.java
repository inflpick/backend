package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

public record InfluencerIntroduction(@NotNull String introduction) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 300;

    public InfluencerIntroduction {
        validateInput(introduction);
    }

    private static void validateInput(@NotNull String introduction) throws CustomConstraintViolationExceptionBase {
        if (introduction.isBlank() || introduction.isEmpty() || introduction.length() > MAX_LENGTH) {
            throw new InfluencerIntroductionValidationFailedException(introduction);
        }
    }

}
