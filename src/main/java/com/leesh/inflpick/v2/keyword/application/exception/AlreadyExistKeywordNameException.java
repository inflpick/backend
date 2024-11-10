package com.leesh.inflpick.v2.keyword.application.exception;

import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

public class AlreadyExistKeywordNameException extends IllegalArgumentException {
    public AlreadyExistKeywordNameException(String name) {
        super("already exist keyword name: " + name);
    }

    public AlreadyExistKeywordNameException(KeywordName name) {
        super("already exist keyword name: " + name.name());
    }
}
