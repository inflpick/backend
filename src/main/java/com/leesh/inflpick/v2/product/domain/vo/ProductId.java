package com.leesh.inflpick.v2.product.domain.vo;

public record ProductId(String id) {

    /* Business Logic */
    public static ProductId create(String id) {
        return new ProductId(id);
    }

    public static ProductId empty() {
        return new ProductId("");
    }

    public boolean isEmpty() {
        return id == null || id.isEmpty();
    }
}
