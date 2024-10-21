package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import com.leesh.inflpick.product.core.domain.exception.InvalidOnlineStoreException;
import com.leesh.inflpick.product.core.domain.exception.ProductDescriptionValidationFailedException;
import com.leesh.inflpick.product.core.domain.exception.ProductNameValidationFailedException;
import org.springframework.http.HttpStatus;

public enum ProductCreateApiErrorCode implements ApiErrorCode {

    PRODUCT_NAME_VALIDATION_FAILED("PR_C_0001", HttpStatus.BAD_REQUEST, "유효한 제품 명을 입력해주세요.", ProductNameValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "제품 명 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    PRODUCT_DESCRIPTION_VALIDATION_FAILED("PR_C_0002", HttpStatus.BAD_REQUEST, "유효한 제품 설명을 입력해주세요.", ProductDescriptionValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "제품 설명 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INVALID_ONLINE_STORE_TYPE("PR_C_0003", HttpStatus.BAD_REQUEST, "유효한 온라인 스토어 타입이 아니에요.", InvalidOnlineStoreException.ERROR_MESSAGE_FORMAT.toPattern(), "온라인 스토어 타입이 유효하지 않은 경우에 발생합니다."),

    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    ProductCreateApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
