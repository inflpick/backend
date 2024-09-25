package com.leesh.inflpick.common.adapter.in.web.value;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public record CursorRequest(
        @Schema(description = "현재 커서 (0부터 시작)", example = "0", defaultValue = "0", implementation = Integer.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer cursor,
        @Schema(description = "한번에 불러올 컨텐츠 개수", example = "20", defaultValue = "20", implementation = Integer.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer limit) {

    private static final int DEFAULT_CURSOR = 0;
    private static final int DEFAULT_LIMIT = 20;

    public CursorRequest(Integer cursor, Integer limit) {
        this.cursor = Objects.requireNonNullElse(cursor, DEFAULT_CURSOR);
        this.limit = Objects.requireNonNullElse(limit, DEFAULT_LIMIT);
    }

}
