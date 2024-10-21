package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.v2.adapter.in.web.common.error.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum ProductGetListsApiErrorCode implements ApiErrorCode {

    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    ProductGetListsApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public String getAction() {
        return this.action;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

}
