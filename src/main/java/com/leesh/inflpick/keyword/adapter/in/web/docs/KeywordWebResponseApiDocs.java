package com.leesh.inflpick.keyword.adapter.in.web.docs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "키워드 조회 응답")
public interface KeywordWebResponseApiDocs {

    @Schema(description = "ID", example = "f103314b-778c-49fc-ae9c-7956794a3bdf", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String id();

    @Schema(description = "키워드 명", example = "기술", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String name();

    @Schema(description = "16진수 색상 코드", example = "#FFFFFF", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String hexColor();
}
