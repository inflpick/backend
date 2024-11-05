package com.leesh.inflpick.v2.product.application.exception;

import com.leesh.inflpick.v2.product.domain.vo.ProductId;

public class ProductNotFoundException extends IllegalArgumentException {
    public ProductNotFoundException(ProductId id) {
        super("Product Not Found, id: " + id.id());
    }
}
