package com.leesh.inflpick.v2.keyword.application.exception;

import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;

public class KeywordNotFoundException extends RuntimeException {
    public KeywordNotFoundException(KeywordId id) {
        super("Keyword not found, id: " + id);
    }
}
