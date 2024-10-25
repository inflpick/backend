package com.leesh.inflpick.v2.product.domain.vo;

public record ProductName(String name) {

    public ProductName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품명은 필수값입니다.");
        }
    }

    public static ProductName create(String name) {
        return new ProductName(name);
    }

    public String value() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
