package com.leesh.inflpick.v2.product.domain.vo;

import com.leesh.inflpick.v2.product.domain.exception.ProductNameFormatException;

import java.util.regex.Pattern;

public record ProductName(String name) {

    // ProductName must be between 1 and 300 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{1,300}$");

    public ProductName {
        if (name == null || name.isEmpty()) {
            name = "";
        } else {
            String stripped = name.strip();
            if (!PATTERN.matcher(stripped).matches()) {
                throw new ProductNameFormatException("ProductName must be between 1 and 300 characters long" + name.length());
            }
            name = stripped;
        }
    }

    /* Business Logic */
    public static ProductName create(String name) {
        return new ProductName(name);
    }

    public static ProductName empty() {
        return new ProductName("");
    }
}
