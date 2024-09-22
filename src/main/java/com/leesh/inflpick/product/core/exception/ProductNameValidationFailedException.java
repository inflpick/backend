package com.leesh.inflpick.product.core.exception;

import com.leesh.inflpick.product.core.value.ProductName;

import java.text.MessageFormat;

public class ProductNameValidationFailedException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("제품명은 최소 %d자 이상, %d자 이하로 입력해주세요.".formatted(ProductName.MIN_LENGTH, ProductName.MAX_LENGTH));

    public ProductNameValidationFailedException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
