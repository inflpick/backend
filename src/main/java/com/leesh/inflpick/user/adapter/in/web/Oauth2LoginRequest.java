package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.RequiredFieldsValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "OAuth2.0 로그인 요청", description = "OAuth2.0 로그인 요청 필드")
@Builder
public record Oauth2LoginRequest(
        @Schema(description = "OAuth2.0 로그인 타입", example = "KAKAO", implementation = Oauth2Type.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String oauth2Type,
        @Schema(description = "인가 코드", example = "123456", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String authorizationCode) {

    public Oauth2LoginRequest(String oauth2Type, String authorizationCode) {
        RequiredFieldsValidator.validate(oauth2Type, authorizationCode);
        this.oauth2Type = oauth2Type.strip();
        this.authorizationCode = authorizationCode.strip();
    }
}

