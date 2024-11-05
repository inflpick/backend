package com.leesh.inflpick.v2.keyword.application.dto;

import com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger.KeywordResponseDocs;
import com.leesh.inflpick.v2.keyword.domain.Keyword;

public record KeywordResponse(String id, String name, String hexColor) implements KeywordResponseDocs {
    public static KeywordResponse create(Keyword keyword) {
        return new KeywordResponse(keyword.id().id(),
                keyword.name().name(),
                keyword.color().hexColor());
    }
}