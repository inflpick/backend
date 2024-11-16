package com.leesh.inflpick.product.domain.exception;

public class ProductNameFormatException extends IllegalArgumentException {

    public ProductNameFormatException() {
        super("Product Name Format is not correct");
    }

    public ProductNameFormatException(String message) {
        super(message);
    }
}
