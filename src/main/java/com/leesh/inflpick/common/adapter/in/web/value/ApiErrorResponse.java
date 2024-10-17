package com.leesh.inflpick.common.adapter.in.web.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.common.adapter.in.web.docs.ApiErrorResponseApiDocs;
import com.leesh.inflpick.common.adapter.out.time.InstantHolder;
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
        String path) implements ApiErrorResponseApiDocs {

        public static ApiErrorResponse of(InstantHolder instantHolder, ApiErrorCode apiErrorCode, String method, String path) {
                return ApiErrorResponse.builder()
                        .timestamp(instantHolder.instant())
                        .status(apiErrorCode.httpStatus().value())
                        .code(apiErrorCode.code())
                        .reason(apiErrorCode.reason())
                        .action(apiErrorCode.action())
                        .method(method)
                        .path(path)
                        .build();
        }

        public static ApiErrorResponse of(InstantHolder instantHolder, ApiErrorCode apiErrorCode, String method, String path, String reason) {
                return ApiErrorResponse.builder()
                        .timestamp(instantHolder.instant())
                        .status(apiErrorCode.httpStatus().value())
                        .code(apiErrorCode.code())
                        .reason(reason)
                        .action(apiErrorCode.action())
                        .method(method)
                        .path(path)
                        .build();
        }
}
