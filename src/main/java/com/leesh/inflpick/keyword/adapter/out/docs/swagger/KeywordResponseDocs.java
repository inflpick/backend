package com.leesh.inflpick.keyword.adapter.out.docs.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "키워드 조회 응답")
public interface KeywordResponseDocs {

    @Schema(description = "ID", example = "6726946b272157735138c837", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String id();

    @Schema(description = "키워드 명", example = "기술", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String name();

    @Schema(description = "16진수 색상 코드", example = "#FFFFFF", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String hexColor();
}
