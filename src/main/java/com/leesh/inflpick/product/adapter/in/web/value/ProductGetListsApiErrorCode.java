package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.product.core.domain.exception.InvalidProductSortTypeException;
import org.springframework.http.HttpStatus;

public enum ProductGetListsApiErrorCode implements ApiErrorCode {

    INVALID_SORT_TYPE("P_L_0001", HttpStatus.BAD_REQUEST, "유효한 정렬 타입을 입력해주세요.", InvalidProductSortTypeException.ERROR_MESSAGE_FORMAT.toPattern(), "정렬 타입이 올바르지 않은 경우에 발생합니다."),
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
