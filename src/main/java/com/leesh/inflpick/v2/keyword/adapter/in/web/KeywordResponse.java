package com.leesh.inflpick.v2.keyword.adapter.in.web;

import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;

public record KeywordResponse(String id, String name, String hexColor) implements KeywordWebResponseDocs {

    public static KeywordResponse from(Keyword keyword) {
        return new KeywordResponse(keyword.getId().getValue(),
                keyword.getName().getValue(),
                keyword.getColor().getValue());
    }
}
