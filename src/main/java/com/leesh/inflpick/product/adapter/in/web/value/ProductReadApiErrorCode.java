package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum ProductReadApiErrorCode implements ApiErrorCode {

    PRODUCT_NOT_FOUND("PR_R_0001", HttpStatus.NOT_FOUND, "제품을 찾을 수 없어요.", "요청한 제품이 존재하지 않아, 요청을 처리할 수 없습니다.", "ID에 해당하는 제품을 찾을 수 없는 경우에 발생합니다."),
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
