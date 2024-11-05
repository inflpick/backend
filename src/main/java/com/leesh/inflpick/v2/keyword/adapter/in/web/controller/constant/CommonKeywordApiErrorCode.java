package com.leesh.inflpick.v2.keyword.adapter.in.web.controller.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonKeywordApiErrorCode implements ApiErrorCode {

    ALREADY_EXIST_KEYWORD("COK_0001", HttpStatus.BAD_REQUEST, "이미 존재하는 키워드 이름이에요.", "키워드 이름을 다시 입력해주세요.", "키워드 이름이 중복될 때 발생합니다."),
    NOT_FOUND_KEYWORD("COK_0002", HttpStatus.NOT_FOUND, "존재하지 않는 키워드에요.", "요청 값을 확인 후 다시 시도해주세요.", "요청한 값을 가진 키워드가 존재하지 않는 경우 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    CommonKeywordApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }
}
