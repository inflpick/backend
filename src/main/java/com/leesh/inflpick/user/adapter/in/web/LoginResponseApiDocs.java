package com.leesh.inflpick.user.adapter.in.web;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "로그인 응답", description = "로그인 응답 필드")
public interface LoginResponseApiDocs {
    @Schema(description = "토큰 타입", example = "Bearer", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String tokenType();
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNjIwNjM4MCwiZXhwIjoxNjI2MjA2MzgwfQ.7J1", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String accessToken();
    @Schema(description = "액세스 토큰 만료 시간 (초)", example = "3600", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Integer accessTokenExpiresInSeconds();
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNjIwNjM4MCwiZXhwIjoxNjI2MjA2MzgwfQ.7J1", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String refreshToken();
    @Schema(description = "리프레시 토큰 만료 시간 (초)", example = "604800", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Integer refreshTokenExpiresInSeconds();
}
