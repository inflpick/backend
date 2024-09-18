package com.leesh.inflpick.keyword.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;

public class KeywordNotFoundException extends ResourceNotFoundException {
    public KeywordNotFoundException(String id) {
        super(id, "키워드");
    }
}
