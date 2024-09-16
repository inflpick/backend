package com.leesh.inflpick.common.adapter.in.web;

import lombok.Getter;

public enum ErrorCode {

    CO_0001("유저 입력값이 올바르지 않음"),
    ;

    @Getter
    private final String cause;

    ErrorCode(String cause) {
        this.cause = cause;
    }
}
