package com.leesh.inflpick.product.domain.exception;

public class MaximumProductKeywordsException extends IllegalArgumentException {

    public MaximumProductKeywordsException() {
        super("Product Keyword size cannot exceed 10");
    }

    public MaximumProductKeywordsException(Integer size) {
        super("Product Keyword size cannot exceed 10, input keyowrd size: " + size);
    }
}
