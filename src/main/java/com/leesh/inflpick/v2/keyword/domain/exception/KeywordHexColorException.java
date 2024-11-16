package com.leesh.inflpick.v2.keyword.domain.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class KeywordHexColorException extends RuntimeException implements ErrorCode {

    public KeywordHexColorException() {
        super("Keyword Color must be hex color code");
    }

    public KeywordHexColorException(String color) {
        super("Keyword Color must be hex color code, but was: " + color);
    }

    @Override
    public String getCode() {
        return "KEYWORD_HEX_COLOR";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "키워드 색상은 16진수 색상 코드여야 합니다.";
    }

    @Override
    public String getAction() {
        return "16진수 색상 코드를 입력해주세요.";
    }

    @Override
    public String getComment() {
        return "키워드 색상이 16진수 색상 코드가 아닌 경우 발생합니다.";
    }
}
