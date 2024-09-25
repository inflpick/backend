package com.leesh.inflpick.product.core.domain.value;

import com.leesh.inflpick.product.core.domain.exception.ProductNameValidationFailedException;
import org.jetbrains.annotations.NotNull;

public record ProductName(@NotNull String name) {

    public static Integer MIN_LENGTH = 1;
    public static Integer MAX_LENGTH = 100;

    public ProductName {
        validateInput(name);
    }

    public static ProductName from(String name) {
        return new ProductName(name);
    }

    private void validateInput(String name) {
        if (name.isBlank() || name.isEmpty() || name.length() > MAX_LENGTH) {
            throw new ProductNameValidationFailedException(name);
        }
    }
}
