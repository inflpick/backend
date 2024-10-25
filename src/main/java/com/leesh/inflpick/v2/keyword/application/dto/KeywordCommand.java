package com.leesh.inflpick.v2.keyword.application.dto;

import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordColor;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

public record KeywordCommand(KeywordName name, KeywordColor color) {

    public Keyword toEntity() {
        return Keyword.builder(name)
                .color(color)
                .build();
    }

}
