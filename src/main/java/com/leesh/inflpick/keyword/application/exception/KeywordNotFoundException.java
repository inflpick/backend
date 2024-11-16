package com.leesh.inflpick.keyword.application.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import org.springframework.http.HttpStatus;

public class KeywordNotFoundException extends RuntimeException implements ErrorCode {

    public KeywordNotFoundException() {
        super("Keyword not found");
    }

    public KeywordNotFoundException(KeywordId id) {
        super("Keyword not found, id: " + id);
    }

    @Override
    public String getCode() {
        return "KEYWORD_NOT_FOUND";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getReason() {
        return "키워드를 찾을 수 없어요.";
    }

    @Override
    public String getAction() {
        return "키워드 ID를 확인 후, 다시 시도해주세요.";
    }

    @Override
    public String getComment() {
        return "키워드 ID가 잘못된 경우에 발생합니다.";
    }
}
