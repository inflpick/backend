package com.leesh.inflpick.keyword.adapter.in.web.value;

import com.leesh.inflpick.keyword.adapter.in.web.docs.KeywordWebResponseApiDocs;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import lombok.Builder;

@Builder
public record KeywordResponse(
        String id,
        String name,
        String hexColor) implements KeywordWebResponseApiDocs {
    public static KeywordResponse from(Keyword keyword) {
        return KeywordResponse.builder()
                .id(keyword.getId())
                .name(keyword.getName())
                .hexColor(keyword.getHexColor())
                .build();
    }
}
