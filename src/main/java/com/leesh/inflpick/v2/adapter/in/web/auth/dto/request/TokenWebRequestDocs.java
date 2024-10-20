package com.leesh.inflpick.v2.adapter.in.web.auth.dto.request;

import com.leesh.inflpick.v2.domain.token.vo.GrantType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "토큰 요청")
public interface TokenWebRequestDocs {

    @Schema(description = "인증 방식 (authorization_code 고정)", implementation = GrantType.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String grantType();

    @Schema(description = "인증 코드", example = "7a726599-abfe-4eaa-91f8-322607303ba0", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String code();
}
