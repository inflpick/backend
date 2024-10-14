package com.leesh.inflpick.keyword.port.in;

import com.leesh.inflpick.common.port.in.exception.DuplicateResourceException;

public class DuplicateKeywordNameException extends DuplicateResourceException {
    public DuplicateKeywordNameException(String field, String input, String resource) {
        super(field, input, resource);
    }
}
