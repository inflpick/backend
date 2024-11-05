package com.leesh.inflpick.v2.token.adapter.in.web.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CreateTokenApiErrorCode implements ApiErrorCode {

    NOT_SUPPORTED_GRANT_TYPE("CT_0001", HttpStatus.BAD_REQUEST, "지원하지 않는 인증 타입이에요.", "다시 시도하거나 인플픽 관리자(info@inflpick.com)에게 문의 바랍니다.", "인증 타입이 잘못된 입력 값일 경우 발생합니다."),
    EXPIRED_AUTHENTICATION_CODE("CT_0002", HttpStatus.BAD_REQUEST, "인증 요청이 만료됐어요.", "재 로그인이 필요해요.", "이미 토큰을 발급 받은 인증 코드로 다시 토큰 발급 요청을 하는 경우 발생합니다."),
    EXPIRED_REFRESH_TOKEN("CT_0003", HttpStatus.BAD_REQUEST, "로그인이 만료되었어요.", "다시 로그인 해주세요.", "갱신 토큰이 만료되어 토큰을 발급할 수 없을 때 발생합니다."),
    INVALID_REFRESH_TOKEN("CT_0004", HttpStatus.BAD_REQUEST, "갱신 토큰이 올바르지 않아요.", "다시 로그인 해주세요.", "유효하지 않은 갱신 토큰을 사용하여 토큰을 발급할 수 없을 때 발생합니다."),
    ;

    CreateTokenApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
