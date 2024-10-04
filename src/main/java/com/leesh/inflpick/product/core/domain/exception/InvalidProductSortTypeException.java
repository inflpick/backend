package com.leesh.inflpick.product.core.domain.exception;

import com.leesh.inflpick.product.port.ProductSortType;

import java.text.MessageFormat;

public class InvalidProductSortTypeException extends RuntimeException {

    public static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("정렬 타입은 %s 중 하나여야 합니다.".formatted(String.join(", ", ProductSortType.availableSortTypes())));

    public InvalidProductSortTypeException(String message) {
        super(message);
    }
}
