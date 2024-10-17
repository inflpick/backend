package com.leesh.inflpick.common.adapter.in.web.docs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "API 에러 응답")
public interface ApiErrorResponseApiDocs {

    @Schema(description = "에러 발생 시간 (UTC)", example = "2021-08-01T00:00:00Z", implementation = Instant.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Instant timestamp();

    @Schema(description = "HTTP 상태 코드 (https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)", example = "400", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    int status();

    @Schema(description = "에러 코드 (시스템 관리자가 사용)", example = "CO_0001", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String code();

    @Schema(description = "사용자에게 보여줄 에러 원인", example = "인플루언서 이름이 올바르지 않아요.", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String reason();

    @Schema(description = "사용자에게 보여줄 에러 해결 방법", example = "인플루언서 이름은 최소 1글자, 최대 300글자 이하여야 합니다.", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String action();

    @Schema(description = "요청 메소드", example = "POST", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String method();

    @Schema(description = "요청 경로", example = "/api/influencers", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String path();
}
