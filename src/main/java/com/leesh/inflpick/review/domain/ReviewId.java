package com.leesh.inflpick.review.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record ReviewId(@NotNull String uuid) {
    public ReviewId {
        Objects.requireNonNull(uuid, "uuid must not be null");
        if (uuid.isBlank()) {
            throw new IllegalArgumentException("uuid must not be blank");
        }
    }
}
