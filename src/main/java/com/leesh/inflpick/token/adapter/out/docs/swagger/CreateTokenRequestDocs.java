package com.leesh.inflpick.token.adapter.out.docs.swagger;

import com.leesh.inflpick.token.domain.vo.GrantType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인증 코드를 통해 로그인 요청")
public interface CreateTokenRequestDocs {

    @Schema(description = "인증 타입", example = "AUTHENTICATION_CODE", implementation = GrantType.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String grantType();

    @Schema(description = "인증 코드 (인증 타입이 \"AUTHENTICATION_CODE\" 타입인 경우에만 필수)", example = "7a726599-abfe-4eaa-91f8-322607303ba0", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    String code();

    @Schema(description = "리프레시 토큰 (인증 타입이 \"REFRESH_TOKEN\" 타입인 경우에만 필수)", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNjIwNjM4MCwiZXhwIjoxNjI2MjA2MzgwfQ.7J1", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    String refreshToken();
}
