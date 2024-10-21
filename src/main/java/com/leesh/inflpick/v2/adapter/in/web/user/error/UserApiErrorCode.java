package com.leesh.inflpick.v2.adapter.in.web.user.error;

import com.leesh.inflpick.v2.adapter.in.web.common.error.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserApiErrorCode implements ApiErrorCode {

    USER_NOT_FOUND("U0001", HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없어요.", "잠시 후 다시 시도하시거나, 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.", "존재하지 않는 사용자를 조회하려고 했을 때 발생합니다."),
    ;

    UserApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
