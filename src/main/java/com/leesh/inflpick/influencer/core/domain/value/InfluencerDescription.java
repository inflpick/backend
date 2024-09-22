package com.leesh.inflpick.influencer.core.domain.value;

import com.leesh.inflpick.influencer.core.domain.exception.InfluencerDescriptionValidationFailedException;
import org.jetbrains.annotations.NotNull;

public record InfluencerDescription(@NotNull String description) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 50000;

    public InfluencerDescription {
        validateInput(description);
    }

    private static void validateInput(@NotNull String description) throws InfluencerDescriptionValidationFailedException {
        if (description.isBlank() || description.isEmpty() || description.length() > MAX_LENGTH) {
            throw new InfluencerDescriptionValidationFailedException(description);
        }
    }

    public static InfluencerDescription from(@NotNull String description) {
        return new InfluencerDescription(description);
    }
}
