package com.leesh.inflpick.product.core.domain.value;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record Price(@NotNull Integer value) {

    public Price {
        Objects.requireNonNull(value, "value must not be null");
        if (value < 0) {
            throw new IllegalArgumentException("value must be greater than or equal to 0");
        }
    }

}
