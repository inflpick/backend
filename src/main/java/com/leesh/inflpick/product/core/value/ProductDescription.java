package com.leesh.inflpick.product.core.value;

import com.leesh.inflpick.product.core.exception.ProductDescriptionValidationFailedException;
import org.jetbrains.annotations.NotNull;

public record ProductDescription(@NotNull String description) {

    public static Integer MIN_LENGTH = 1;
    public static Integer MAX_LENGTH = 10000;

    public ProductDescription {
        validateInput(description);
    }

    public static ProductDescription from(String description) {
        return new ProductDescription(description);
    }

    private void validateInput(String description) {
        if (description.isBlank() || description.isEmpty() || description.length() > MAX_LENGTH) {
            throw new ProductDescriptionValidationFailedException(description);
        }
    }
}
