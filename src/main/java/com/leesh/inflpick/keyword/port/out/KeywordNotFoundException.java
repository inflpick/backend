package com.leesh.inflpick.keyword.port.out;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;

public class KeywordNotFoundException extends ResourceNotFoundException {
    public KeywordNotFoundException(String id) {
        super(id, "키워드");
    }
}
