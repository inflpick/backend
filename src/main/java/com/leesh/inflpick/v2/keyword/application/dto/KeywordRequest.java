package com.leesh.inflpick.v2.keyword.application.dto;

import com.leesh.inflpick.v2.common.application.service.RequiredFieldsValidator;
import com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger.KeywordRequestDocs;
import com.leesh.inflpick.v2.keyword.domain.Keyword;

public record KeywordRequest(String name, String hexColor) implements KeywordRequestDocs {

    public KeywordRequest(String name, String hexColor) {
        RequiredFieldsValidator.validate(name, hexColor);
        this.name = name.strip();
        this.hexColor = hexColor.strip();
    }

    public Keyword toEntity() {
        return Keyword.withoutId(name, hexColor);
    }
}
