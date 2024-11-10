package com.leesh.inflpick.v2.product.domain.exception;

public class MaximumProductKeywordsException extends IllegalArgumentException {
    public MaximumProductKeywordsException(Integer size) {
        super("Product Keyword size cannot exceed 10, input keyowrd size: " + size);
    }
}
