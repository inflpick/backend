package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum KeywordReadApiErrorCode implements ApiErrorCode {
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    KeywordReadApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
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

    @Override
    public String comment() {
        return this.comment;
    }
}
