package com.leesh.inflpick.v2.common.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorResponseDocs;
import com.leesh.inflpick.v2.common.domain.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;

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

        public static ApiErrorResponse create(HttpServletRequest request, ErrorCode errorCode) {
                return new ApiErrorResponse(
                        Instant.now(),
                        errorCode.getHttpStatus().value(),
                        errorCode.getCode(),
                        errorCode.getReason(),
                        errorCode.getAction(),
                        request.getMethod(),
                        request.getRequestURI(),
                        errorCode.getComment()
                );
        }
}
