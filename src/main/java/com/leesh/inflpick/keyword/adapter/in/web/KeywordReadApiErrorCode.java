package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum KeywordReadApiErrorCode implements ApiErrorCode {
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;

    KeywordReadApiErrorCode(String code, HttpStatus httpStatus, String reason, String action) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    @Override
    public String reason() {
        return this.reason;
    }

    @Override
    public String action() {
        return this.action;
    }
}
