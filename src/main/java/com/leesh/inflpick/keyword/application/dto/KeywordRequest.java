package com.leesh.inflpick.keyword.application.dto;

import com.leesh.inflpick.common.application.service.RequiredFieldsValidator;
import com.leesh.inflpick.keyword.adapter.out.docs.swagger.KeywordRequestDocs;
import com.leesh.inflpick.keyword.domain.Keyword;

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
