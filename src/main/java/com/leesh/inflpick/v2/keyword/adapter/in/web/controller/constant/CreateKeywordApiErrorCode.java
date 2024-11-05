package com.leesh.inflpick.v2.keyword.adapter.in.web.controller.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CreateKeywordApiErrorCode implements ApiErrorCode {

    INVALID_KEYWORD("CK_0001", HttpStatus.BAD_REQUEST, "키워드 이름이 유효하지 않아요.", "키워드 이름은 20자리 이내로 입력해주세요.", "키워드 값이 유효하지 않을 때 발생합니다."),
    INVALID_HEX_COLOR("CK_0002", HttpStatus.BAD_REQUEST, "키워드 색상이 유효하지 않아요.", "키워드 색상은 6자리의 HEX 코드로 입력해주세요.", "키워드 색상 값이 유효하지 않을 때 발생합니다.")
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    CreateKeywordApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }
}
