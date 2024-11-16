package com.leesh.inflpick.v2.user.application.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException implements ErrorCode {

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(UserId id) {
        super("User not found: " + id);
    }

    @Override
    public String getCode() {
        return "USER_NOT_FOUND";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getReason() {
        return "유저 정보를 찾을 수 없어요.";
    }

    @Override
    public String getAction() {
        return "요청한 유저 정보를 찾을 수 없습니다.";
    }

    @Override
    public String getComment() {
        return "존재하지 않는 사용자를 조회하려고 했을 때 발생합니다.";
    }
}
