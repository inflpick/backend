package com.leesh.inflpick.v2.adapter.in.web.auth.error;

import com.leesh.inflpick.v2.adapter.in.web.common.error.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthApiErrorCode implements ApiErrorCode {

    DUPLICATED_SOCIAL_USER("A0001", HttpStatus.CONFLICT, "이미 가입된 소셜 계정입니다.", "다른 소셜 계정으로 회원 가입 바랍니다.", "이미 가입된 소셜 계정으로 다시 가입하려고 했을 때 발생합니다."),
    CREATE_TOKEN_FAILED("A0002", HttpStatus.INTERNAL_SERVER_ERROR, "토큰 생성에 실패했습니다.", "다시 시도하거나 관리자에게 문의 바랍니다.", "토큰 생성에 실패했을 때 발생합니다."),
    ;

    AuthApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
