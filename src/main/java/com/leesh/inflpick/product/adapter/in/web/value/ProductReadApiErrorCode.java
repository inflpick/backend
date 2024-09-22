package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum ProductReadApiErrorCode implements ApiErrorCode {

    PRODUCT_NOT_FOUND("PR_R_0001", HttpStatus.NOT_FOUND, "제품을 찾을 수 없어요.", "요청한 제품이 존재하지 않아, 조회할 수 없습니다.", "UUID에 해당하는 제품을 찾을 수 없는 경우에 발생합니다."),

    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    ProductReadApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
