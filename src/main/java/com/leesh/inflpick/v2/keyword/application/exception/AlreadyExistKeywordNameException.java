package com.leesh.inflpick.v2.keyword.application.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import org.springframework.http.HttpStatus;

public class AlreadyExistKeywordNameException extends RuntimeException implements ErrorCode {

    public AlreadyExistKeywordNameException() {
        super("already exist keyword name");
    }

    public AlreadyExistKeywordNameException(String name) {
        super("already exist keyword name: " + name);
    }

    public AlreadyExistKeywordNameException(KeywordName name) {
        super("already exist keyword name: " + name.name());
    }

    @Override
    public String getCode() {
        return "ALREADY_EXIST_KEYWORD_NAME";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "이미 존재하는 키워드 이름이에요.";
    }

    @Override
    public String getAction() {
        return "다른 이름으로 다시 시도해주세요.";
    }

    @Override
    public String getComment() {
        return "이미 존재하는 키워드 이름을 사용하여 키워드를 생성하려고 시도한 경우에 발생합니다.";
    }
}
