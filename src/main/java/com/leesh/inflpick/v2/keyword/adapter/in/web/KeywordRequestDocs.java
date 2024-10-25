package com.leesh.inflpick.v2.keyword.adapter.in.web;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "키워드 요청")
public interface KeywordRequestDocs {

    @Schema(description = "키워드명(최소 1자, 최대 30자)",
            example = "100만 유튜버",
            implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = KeywordName.MIN_LENGTH,
            maxLength = KeywordName.MAX_LENGTH)
    String name();


    @Schema(description = "16진수 색상 코드",
            example = "#FFFFFF",
            implementation = String.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    String hexColor();
}
