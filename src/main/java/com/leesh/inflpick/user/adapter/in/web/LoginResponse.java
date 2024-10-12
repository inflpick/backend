package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.user.port.out.AuthenticationToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "로그인 응답", description = "로그인 응답 필드")
@Builder
public record LoginResponse(
        @Schema(description = "토큰 타입", example = "Bearer", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String tokenType,
        @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNjIwNjM4MCwiZXhwIjoxNjI2MjA2MzgwfQ.7J1", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String accessToken,
        @Schema(description = "액세스 토큰 만료 시간 (초)", example = "3600", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
        Integer accessTokenExpiresInSeconds,
        @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNjIwNjM4MCwiZXhwIjoxNjI2MjA2MzgwfQ.7J1", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String refreshToken,
        @Schema(description = "리프레시 토큰 만료 시간 (초)", example = "604800", implementation = Integer.class, requiredMode = Schema.RequiredMode.REQUIRED)
        Integer refreshTokenExpiresInSeconds) {

        public static LoginResponse of(AuthenticationToken accessToken, AuthenticationToken refreshToken) {
                return LoginResponse.builder()
                        .tokenType("Bearer")
                        .accessToken(accessToken.value())
                        .accessTokenExpiresInSeconds(accessToken.expiresInSeconds())
                        .refreshToken(refreshToken.value())
                        .refreshTokenExpiresInSeconds(refreshToken.expiresInSeconds())
                        .build();
        }
}
