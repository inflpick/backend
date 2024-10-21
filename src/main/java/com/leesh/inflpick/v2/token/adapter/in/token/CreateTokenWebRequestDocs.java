package com.leesh.inflpick.v2.token.adapter.in.token;

import com.leesh.inflpick.v2.token.domain.vo.GrantType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인증 코드를 통해 로그인 요청")
interface CreateTokenWebRequestDocs {

    @Schema(description = "인증 타입", example = "authentication_code", implementation = GrantType.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String grantType();

    @Schema(description = "인증 코드 (AUTHENTICATION_CODE 타입인 경우에만 필수)", example = "7a726599-abfe-4eaa-91f8-322607303ba0", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    String code();

    @Schema(description = "리프레시 토큰 (REFRESH_TOKEN 타입인 경우에만 필수)", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNjIwNjM4MCwiZXhwIjoxNjI2MjA2MzgwfQ.7J1", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    String refreshToken();
}
