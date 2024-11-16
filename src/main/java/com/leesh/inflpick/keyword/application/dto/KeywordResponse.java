package com.leesh.inflpick.keyword.application.dto;

import com.leesh.inflpick.keyword.adapter.out.docs.swagger.KeywordResponseDocs;
import com.leesh.inflpick.keyword.domain.Keyword;

public record KeywordResponse(String id, String name, String hexColor) implements KeywordResponseDocs {
    public static KeywordResponse create(Keyword keyword) {
        return new KeywordResponse(keyword.id().id(),
                keyword.name().name(),
                keyword.color().hexColor());
    }
}