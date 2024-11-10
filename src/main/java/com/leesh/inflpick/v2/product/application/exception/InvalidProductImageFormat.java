package com.leesh.inflpick.v2.product.application.exception;

public class InvalidProductImageFormat extends IllegalArgumentException {
    public InvalidProductImageFormat(String originalFilename) {
        super("Product image format is not supported, file: %s".formatted(originalFilename));
    }
}
