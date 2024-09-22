package com.leesh.inflpick.product.core.value;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public record ProductImage(@NotNull String imagePath) {

    public static ProductImage from(@NotNull String productImagePath) {
        return new ProductImage(productImagePath);
    }

    public static ProductImage empty() {
        return new ProductImage("");
    }

    public Path basePath(String id) {
        return Path.of("/products", id, "/product-image");
    }
}
