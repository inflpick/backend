package com.leesh.inflpick.common.adapter.in.web;

import lombok.Getter;

@Getter
public abstract class BaseBusinessException extends RuntimeException {

    private final ApiErrorCode apiErrorCode;

    public BaseBusinessException(String logMessage, ApiErrorCode apiErrorCode) {
        super(logMessage);
        this.apiErrorCode = apiErrorCode;
    }
}
