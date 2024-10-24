package com.leesh.inflpick.v2.shared.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.Instant;

@Builder
public record ApiErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant timestamp,
        int status,
        String code,
        String reason,
        String action,
        String method,
        String path,
        String comment) implements ApiErrorResponseDocs {
}
