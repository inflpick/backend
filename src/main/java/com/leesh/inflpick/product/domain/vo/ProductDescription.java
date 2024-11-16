package com.leesh.inflpick.product.domain.vo;

import java.util.regex.Pattern;

public record ProductDescription(String description) {

    // ProductDescription must be within 50000 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{0,50000}$");

    public ProductDescription {
        if (!PATTERN.matcher(description).matches()) {
            throw new IllegalArgumentException("ProductDescription must be within 50000 characters long, but was: " + description.length());
        }
    }

    /* Business Logic */
    public static ProductDescription create(String description) {
        return new ProductDescription(description);
    }

    public static ProductDescription empty() {
        return new ProductDescription("");
    }
}
