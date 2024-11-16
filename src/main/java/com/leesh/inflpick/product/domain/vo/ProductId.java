package com.leesh.inflpick.product.domain.vo;

public record ProductId(String id) {

    /* Business Logic */
    public static ProductId create(String id) {
        return new ProductId(id);
    }

    public static ProductId empty() {
        return new ProductId(null);
    }

    public boolean isEmpty() {
        return id == null || id.isEmpty();
    }
}
