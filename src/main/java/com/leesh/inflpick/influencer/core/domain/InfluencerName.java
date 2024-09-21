package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

public record InfluencerName(@NotNull String name) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 300;

    public InfluencerName {
        validateInput(name);
    }

    public static InfluencerName from(@NotNull String name) {
        return new InfluencerName(name);
    }

    private void validateInput(@NotNull String name) throws InfluencerNameValidationFailedException {
        if (name.isBlank() || name.isEmpty() || name.length() > MAX_LENGTH) {
            throw new InfluencerNameValidationFailedException(name);
        }
    }
}
