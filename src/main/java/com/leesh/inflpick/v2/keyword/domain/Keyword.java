package com.leesh.inflpick.v2.keyword.domain;

import com.leesh.inflpick.v2.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordColor;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

import java.time.Instant;

public record Keyword(KeywordId id,
                      KeywordName name,
                      KeywordColor color,
                      Instant createdDate,
                      String createdBy,
                      Instant lastModifiedDate,
                      String lastModifiedBy) {

    /* Business Logic */
    public static Keyword withId(KeywordId id,
                                 KeywordName name,
                                 KeywordColor color,
                                 Instant createdDate,
                                 String createdBy,
                                 Instant lastModifiedDate,
                                 String lastModifiedBy) {
        return new Keyword(id, name, color, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
    }

    public static Keyword withoutId(String name, String color) {
        KeywordId keywordId = KeywordId.empty();
        KeywordName keywordName = KeywordName.create(name);
        KeywordColor keywordColor = KeywordColor.create(color);
        return new Keyword(keywordId, keywordName, keywordColor, null, null, null, null);
    }

    public Keyword update(KeywordRequest request) {
        KeywordName keywordName = KeywordName.create(request.name());
        KeywordColor keywordColor = KeywordColor.create(request.hexColor());
        return withId(id, keywordName, keywordColor, createdDate, createdBy, Instant.now(), lastModifiedBy);
    }
}
