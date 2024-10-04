package com.leesh.inflpick.product.core.domain.exception;

import com.leesh.inflpick.product.core.domain.value.OnlineStore;

import java.text.MessageFormat;

public class InvalidOnlineStoreException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("온라인 스토어는 %s 중 하나여야 합니다.".formatted(OnlineStore.availablePlatforms()));

    public InvalidOnlineStoreException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
