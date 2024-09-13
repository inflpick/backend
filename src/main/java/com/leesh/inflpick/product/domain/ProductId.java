package com.leesh.inflpick.product.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record ProductId(@NotNull String uuid) {

    public ProductId {
        Objects.requireNonNull(uuid, "uuid must not be null");
        if (uuid.isBlank()) {
            throw new IllegalArgumentException("uuid must not be blank");
        }
    }
}
