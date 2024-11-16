package com.leesh.inflpick.v2.common.adapter.in.web.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotAdminUserException extends RuntimeException implements ErrorCode {

    public NotAdminUserException() {
        super("접근 권한이 없어요.");
    }

    @Override
    public String getCode() {
        return "NOT_ADMIN_USER";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }

    @Override
    public String getReason() {
        return "접근 권한이 없어요.";
    }

    @Override
    public String getAction() {
        return "관리자 권한을 확인 후 다시 시도해주세요.";
    }

    @Override
    public String getComment() {
        return "관리자 권한이 없는 사용자가 관리자 권한이 필요한 API 요청을 하는 경우에 발생합니다.";
    }
}
