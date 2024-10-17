package com.leesh.inflpick.common.adapter.in.web.docs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "페이지 응답")
public interface WebPageResponseApiDocs {

    @Schema(description = "현재 페이지", example = "0", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    int currentPage();

    @Schema(description = "전체 페이지 수", example = "10", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    int totalPages();

    @Schema(description = "페이지 크기", example = "10", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    int size();

    @Schema(description = "적용된 정렬 방법", example = "[\"createdDate,desc\"]", implementation = String[].class, requiredMode = Schema.RequiredMode.REQUIRED)
    String sorts();

    @Schema(description = "총 컨텐츠 개수", example = "100", implementation = Long.class, requiredMode = Schema.RequiredMode.REQUIRED)
    long totalElements();
}
