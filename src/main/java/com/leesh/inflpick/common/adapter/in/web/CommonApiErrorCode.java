package com.leesh.inflpick.common.adapter.in.web;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonApiErrorCode implements ApiErrorCode {

    SERVER_ERROR("C_O_0001", HttpStatus.INTERNAL_SERVER_ERROR, "현재 서버가 요청을 처리할 수 없는 상태입니다.", "인플픽 관리자에게 문의 바랍니다.", "예기치 못한 에러로 인해 서버가 요청을 처리할 수 없는 경우에 발생합니다."),
    TOO_MANY_REQUESTS("C_O_0002", HttpStatus.TOO_MANY_REQUESTS, "너무 많은 요청으로 인해 서버가 처리할 수 없습니다.", "잠시 후 다시 시도해 주세요.", "짧은 시간 동안 많은 API 요청되는 경우에 발생합니다."),
    SERVICE_UNAVAILABLE("C_O_0003", HttpStatus.SERVICE_UNAVAILABLE, "서버 임시 점검 중", "잠시 후 다시 시도해 주세요.", "현재 서버가 점검 중이거나 서비스를 제공할 수 없는 상태인 경우에 발생합니다."),
    MISSING_REQUIRED_FIELDS("C_O_0004", HttpStatus.BAD_REQUEST, "필수 입력 값이 입력되지 않았습니다.", "필수 입력 값을 입력 후 다시 요청해주세요.", "API 요청 시, 필수 입력 값이 입력되지 않은 경우에 발생합니다."),
    RESOURCE_NOT_FOUND("C_O_0005", HttpStatus.NOT_FOUND, "해당 UUID를 가진 리소스를 찾을 수 없습니다.", "UUID를 확인 후 다시 시도해주세요.", "해당 UUID를 가진 리소스를 찾을 수 없는 경우에 발생합니다."),
    INVALID_REQUEST_BODY("C_O_0006", HttpStatus.BAD_REQUEST, "요청 본문이 올바르지 않습니다.", "요청 본문을 확인 후 다시 요청해주세요.", "API 요청 본문이 올바르지 않은 경우에 발생합니다. (ex. Json 형식이 올바르지 않은 경우)"),
    UNSUPPORTED_HTTP_MEDIA_TYPE("C_O_0007", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP Media 타입입니다.", "HTTP Media 타입을 확인 후 다시 요청해주세요.", "API 요청 시, 지원하지 않는 HTTP Media 타입을 사용한 경우에 발생합니다."),
    ;

    CommonApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

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

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
