package com.leesh.inflpick.v2.influencer.adapter.in.web.dto;

import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;

public record KeywordWebResponse(String id, String name, String hexColor) {
    public static KeywordWebResponse from(Keyword keyword) {
        return new KeywordWebResponse(keyword.getId().getId(),
                keyword.getName().getValue(),
                keyword.getColor().getValue());
    }
}