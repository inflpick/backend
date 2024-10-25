package com.leesh.inflpick.v2.keyword.adapter.in.web;

import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordCommand;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordColor;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

public record KeywordRequest(String name, String hexColor) implements KeywordRequestDocs {

    public KeywordRequest(String name, String hexColor) {
        RequiredFieldsValidator.validate(name, hexColor);
        this.name = name.strip();
        this.hexColor = hexColor.strip();
    }

    public KeywordCommand toCommand() {
        KeywordName keywordName = KeywordName.create(name);
        KeywordColor color = KeywordColor.create(hexColor);
        return new KeywordCommand(keywordName, color);
    }
}
