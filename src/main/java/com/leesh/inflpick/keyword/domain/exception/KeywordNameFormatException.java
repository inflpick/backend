package com.leesh.inflpick.keyword.domain.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class KeywordNameFormatException extends RuntimeException implements ErrorCode {

    public KeywordNameFormatException() {
        super("Keyword Name must be between 1 and 20 characters");
    }

    public KeywordNameFormatException(String name) {
        super("Keyword Name must be between 1 and 20 characters, but was: " + name);
    }

    @Override
    public String getCode() {
        return "KEYWORD_NAME_FORMAT";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "키워드 이름은 1자 이상 20자 이하여야 합니다.";
    }

    @Override
    public String getAction() {
        return "키워드 이름을 1자 이상 20자 이하로 입력해주세요.";
    }

    @Override
    public String getComment() {
        return "키워드 이름이 1자 이상 20자 이하가 아닌 경우 발생합니다.";
    }
}
