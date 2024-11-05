package com.leesh.inflpick.v2.review.domain;

import com.leesh.inflpick.v2.product.domain.vo.ProductId;

public record ReviewProduct(ProductId id) {

    /* Business Logic */
    public static ReviewProduct create(ProductId id) {
        return new ReviewProduct(id);
    }

    public static ReviewProduct empty() {
        return new ReviewProduct(ProductId.empty());
    }
}
