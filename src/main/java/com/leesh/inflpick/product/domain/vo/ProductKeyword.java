package com.leesh.inflpick.product.domain.vo;

import com.leesh.inflpick.keyword.domain.vo.KeywordColor;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;

public record ProductKeyword(KeywordId id,
                             KeywordName name,
                             KeywordColor color) {

    /* Business Logic */
    public static ProductKeyword create(KeywordId id, KeywordName name, KeywordColor color) {
        return new ProductKeyword(id, name, color);
    }

    public static ProductKeyword empty() {
        return new ProductKeyword(KeywordId.empty(), KeywordName.empty(), KeywordColor.withDefault());
    }
}
