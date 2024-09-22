package com.leesh.inflpick.influencer.core.domain.value;

import com.leesh.inflpick.influencer.core.domain.exception.InfluencerIntroductionValidationFailedException;
import org.jetbrains.annotations.NotNull;

public record InfluencerIntroduction(@NotNull String introduction) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 300;

    public InfluencerIntroduction {
        validateInput(introduction);
    }

    private static void validateInput(@NotNull String introduction) throws InfluencerIntroductionValidationFailedException {
        if (introduction.isBlank() || introduction.isEmpty() || introduction.length() > MAX_LENGTH) {
            throw new InfluencerIntroductionValidationFailedException(introduction);
        }
    }

    public static InfluencerIntroduction from(@NotNull String introduction) {
        return new InfluencerIntroduction(introduction);
    }
}
