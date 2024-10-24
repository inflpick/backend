package com.leesh.inflpick.keyword.adapter.in.web.value;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import com.leesh.inflpick.keyword.core.domain.KeywordNameValidationFailedException;
import org.springframework.http.HttpStatus;

public enum KeywordCreateApiErrorCode implements ApiErrorCode {

    KEYWORD_NAME_VALIDATE_FAILED("K_C_0001", HttpStatus.BAD_REQUEST, "유효한 키워드 이름을 입력해주세요.", KeywordNameValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "키워드 이름 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    KEYWORD_COLOR_VALIDATION_FAILED("K_C_0002", HttpStatus.BAD_REQUEST, "키워드 색상이 HEX 코드 형식이 아니에요.", "키워드 색상을 확인 후, HEX 코드 형식으로 다시 입력해주세요.", "키워드 색상 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    DUPLICATE_KEYWORD_NAME_EXCEPTION("K_C_0003", HttpStatus.CONFLICT, "이미 존재하는 키워드 이름이에요.", "다른 키워드 명을 사용해주세요.", "이미 존재하는 키워드 명을 생성하는 경우에 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    KeywordCreateApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
