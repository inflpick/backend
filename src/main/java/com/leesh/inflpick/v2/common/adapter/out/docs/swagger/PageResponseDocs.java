package com.leesh.inflpick.v2.common.adapter.out.docs.swagger;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "페이징 응답", description = "페이징 응답")
public interface PageResponseDocs {

    @Schema(description = "컨텐츠", example = "[]", implementation = ArraySchema.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Object contents();
    @Schema(description = "현재 페이지", example = "1", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Integer currentPage();
    @Schema(description = "전체 페이지 수", example = "1", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Integer totalPages();
    @Schema(description = "페이지 크기", example = "10", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Integer size();
    @Schema(description = "전체 요소 수", example = "0", implementation = Long.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Long totalElements();
    @Schema(description = "적용된 정렬 속성", example = "[]", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String sortProperties();

}
