package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.keyword.core.domain.Keyword;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "키워드 조회 응답")
@Builder
public record KeywordResponse(
        @Schema(description = "고유 식별자(UUID)", example = "f103314b-778c-49fc-ae9c-7956794a3bdf", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String uuid,
        @Schema(description = "키워드 명", example = "100만 유튜버", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @Schema(description = "16진수 색상 코드", example = "#FFFFFF", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String hexColor) {
    public static KeywordResponse from(Keyword keyword) {
        return KeywordResponse.builder()
                .uuid(keyword.getUuid())
                .name(keyword.getName())
                .hexColor(keyword.getHexColor())
                .build();
    }
}
