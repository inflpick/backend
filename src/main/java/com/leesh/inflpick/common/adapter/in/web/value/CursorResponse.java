package com.leesh.inflpick.common.adapter.in.web.value;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collection;

public record CursorResponse<T>(
        @Schema(description = "요청 컨텐츠 수", example = "20", implementation = Integer.class)
        Integer limit,
        @Schema(description = "컨텐츠 목록")
        Collection<T> contents,
        @Schema(description = "다음 페이지 존재 여부", example = "true", implementation = Boolean.class)
        Boolean hasNext) {
}
