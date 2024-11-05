package com.leesh.inflpick.v2.product.adapter.in.web.dto;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum CreateProductApiErrorCode implements ApiErrorCode {

    PRODUCT_NAME_VALIDATION_FAILED("P_C_0001", HttpStatus.BAD_REQUEST, "유효한 제품 명을 입력해주세요.", "제품명은 1-300자 로만 입력할 수 있어요.", "제품명 필드가 유효성 검증에 실패한 경우에 발생합니다."),
//    PRODUCT_DESCRIPTION_VALIDATION_FAILED("P_C_0002", HttpStatus.BAD_REQUEST, "유효한 제품 설명을 입력해주세요.", ProductDescriptionValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "제품 설명 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INVALID_ONLINE_STORE_TYPE("P_C_0003", HttpStatus.BAD_REQUEST, "지원하지 않는 온라인 스토어 플랫폼이에요.", "온라인 스토어 플랫폼 요청 필드를 확인 후 다시 시도해주세요.", "온라인 스토어 플랫폼 필드 값이 유효하지 않은 경우에 발생합니다."),

    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    CreateProductApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
