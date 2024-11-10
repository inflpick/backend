package com.leesh.inflpick.v2.product.adapter.in.web.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonProductApiErrorCode implements ApiErrorCode {

    NOT_FOUND("COP_0001", HttpStatus.NOT_FOUND, "해당 제품을 찾을 수 없어요.", "요청한 값을 확인 후 다시 시도해주세요.", "요청한 값을 가진 제품을 찾을 수 없을 때 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    CommonProductApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }
}
