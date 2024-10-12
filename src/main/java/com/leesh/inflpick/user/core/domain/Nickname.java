package com.leesh.inflpick.user.core.domain;

import com.leesh.inflpick.influencer.core.domain.exception.InfluencerNameValidationFailedException;
import org.jetbrains.annotations.NotNull;

public record Nickname(@NotNull String name) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 300;

    public Nickname {
        validateInput(name);
    }

    public static Nickname from(@NotNull String name) {
        return new Nickname(name);
    }

    private void validateInput(@NotNull String name) throws UsernameValidationFailedException {
        if (name.isBlank() || name.isEmpty() || name.length() > MAX_LENGTH) {
            throw new InfluencerNameValidationFailedException(name);
        }
    }
}