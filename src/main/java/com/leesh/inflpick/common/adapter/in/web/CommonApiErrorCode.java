package com.leesh.inflpick.common.adapter.in.web;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonApiErrorCode implements ApiErrorCode {

    SERVER_ERROR("CO_0001", HttpStatus.INTERNAL_SERVER_ERROR, "현재 서버가 요청을 처리할 수 없는 상태입니다.", "인플픽 관리자에게 문의 바랍니다."),
    TOO_MANY_REQUESTS("CO_0002", HttpStatus.TOO_MANY_REQUESTS, "너무 많은 요청으로 인해 서버가 처리할 수 없습니다.", "잠시 후 다시 시도해 주세요."),
    SERVICE_UNAVAILABLE("CO_0003", HttpStatus.SERVICE_UNAVAILABLE, "서버 임시 점검 중", "잠시 후 다시 시도해 주세요."),
    MISSING_REQUIRED_FIELDS("CO_0004", HttpStatus.BAD_REQUEST, "필수 입력 값이 입력되지 않았습니다.", "필수 입력 값을 입력 후 다시 요청해주세요."),
    RESOURCE_NOT_FOUND("CO_0005", HttpStatus.NOT_FOUND, "해당 ID를 가진 리소스를 찾을 수 없습니다.", "ID를 확인 후 다시 시도해주세요."),
    INVALID_REQUEST_BODY("CO_0006", HttpStatus.BAD_REQUEST, "요청 본문이 올바르지 않습니다. 주로 올바르지 못한 Json 형식으로 요청을 보내는 경우 발생합니다.", "요청 본문을 확인 후 다시 요청해주세요."),
    ;

    CommonApiErrorCode(String code, HttpStatus httpStatus, String reason, String action) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
    }

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;

    @Override
    public String reason() {
        return this.reason;
    }

    @Override
    public String action() {
        return this.action;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
