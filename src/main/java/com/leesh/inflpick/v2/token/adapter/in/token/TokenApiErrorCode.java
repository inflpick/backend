package com.leesh.inflpick.v2.token.adapter.in.token;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
enum TokenApiErrorCode implements ApiErrorCode {

    EXPIRED_REFRESH_TOKEN("A0001", HttpStatus.BAD_REQUEST, "로그인이 만료되었어요.", "다시 로그인 해주세요.", "갱신 토큰이 만료되어 토큰을 발급할 수 없을 때 발생합니다."),
    INVALID_TOKEN("A0002", HttpStatus.BAD_REQUEST, "접근 토큰이 올바르지 않아요.", "다시 로그인 해주세요.", "유효하지 않은 토큰을 사용하여 토큰을 발급할 수 없을 때 발생합니다."),
    NOT_SUPPORTED_GRANT_TYPE("A0003", HttpStatus.BAD_REQUEST, "인증 요청 정보가 올바르지 않아요.", "다시 시도하거나 인플픽 관리자(info@inflpick.com)에게 문의 바랍니다.", "인증 타입이 잘못된 입력 값일 경우 발생합니다."),
    EXPIRED_AUTHENTICATION_CODE("A0004", HttpStatus.BAD_REQUEST, "인증 요청이 만료됐어요.", "다시 로그인하거나, 문제가 반복될 경우 인플픽 관리자(info@inflpick.com)에게 문의 바랍니다.", "이미 토큰을 발급 받은 인증 코드로 다시 요청을 하는 경우 발생합니다."),

    ;

    TokenApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
}
