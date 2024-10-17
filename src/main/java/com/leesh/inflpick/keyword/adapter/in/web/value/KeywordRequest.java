package com.leesh.inflpick.keyword.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.exception.MissingRequiredFieldsException;
import com.leesh.inflpick.keyword.core.domain.KeywordColor;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import com.leesh.inflpick.keyword.port.in.KeywordCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.jetbrains.annotations.Nullable;

@Builder
public record KeywordRequest(
        String name,
        String hexColor) {

    public KeywordRequest(@Nullable String name,
                          @Nullable String hexColor) {
        validateRequiredFields(name, hexColor);
        this.name = name.strip();
        assert hexColor != null;
        this.hexColor = hexColor.strip();
    }

    private void validateRequiredFields(@Nullable String name,
                                        @Nullable String hexColor) {
        if (name == null || name.isBlank()) {
            throw new MissingRequiredFieldsException("키워드명은 필수 입력값입니다.");
        }
        if (hexColor == null || hexColor.isBlank()) {
            throw new MissingRequiredFieldsException("16진수 색상 코드는 필수 입력 값입니다.");
        }
    }

    public KeywordCommand toCommand() {
        KeywordName keywordName = new KeywordName(name);
        KeywordColor color = KeywordColor.from(hexColor);
        return new KeywordCommand(keywordName, color);
    }
}
