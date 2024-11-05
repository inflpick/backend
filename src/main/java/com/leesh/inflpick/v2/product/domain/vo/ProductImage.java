package com.leesh.inflpick.v2.product.domain.vo;

public record ProductImage(String path) {

    /* Business Logic */
    public static ProductImage create(String path) {
        return new ProductImage(path);
    }

    public static ProductImage empty() {
        return new ProductImage("");
    }
}
