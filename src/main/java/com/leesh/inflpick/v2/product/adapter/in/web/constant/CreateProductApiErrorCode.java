package com.leesh.inflpick.v2.product.adapter.in.web.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CreateProductApiErrorCode implements ApiErrorCode {

    NOT_SUPPORTED_ONLINE_STORE_TYPE("CP_0001", HttpStatus.BAD_REQUEST, "지원하지 않는 온라인 스토어 타입이에요.", "온라인 스토어 타입 요청 값을 확인 해주세요.", "지원하지 않는 온라인 스토어 타입으로 요청 시 발생합니다."),
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
}
