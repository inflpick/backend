package com.leesh.inflpick.common.application.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class ThirdPartyStorageException extends RuntimeException implements ErrorCode {

    public ThirdPartyStorageException() {
        super("Third party storage error");
    }

    public ThirdPartyStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return "THIRD_PARTY_STORAGE_ERROR";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getReason() {
        return "외부 저장소 연동 중 오류가 발생했어요.";
    }

    @Override
    public String getAction() {
        return "외부 저장소 연동 상태를 확인 후 다시 시도해주세요.";
    }

    @Override
    public String getComment() {
        return "외부 저장소 연동 중 오류가 발생한 경우에 발생합니다.";
    }
}
