package com.leesh.inflpick.user.v2.adapter.docs.swagger;

import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Provider;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "유저 응답")
public interface UserWebResponseDocs {

    @Schema(description = "ID", example = "f103314b-778c-49fc-ae9c-7956794a3bdf", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String id();

    @Schema(description = "유저 닉네임", example = "inflpicker", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String nickname();

    @Schema(description = "유저 프로필 이미지 URL", example = "https://cdn.inflpick.com/user-profile-image.jpg", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String profileImageUrl();

    @Schema(description = "유저 이메일", example = "user@gmail.com", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String email();

    @Schema(description = "유저 권한", example = "kakao", implementation = Oauth2Provider.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String role();

    @Schema(description = "유저 소셜 로그인 타입", example = "kakao", implementation = Oauth2Provider.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String oauth2Provider();

    @Schema(description = "유저 소셜 로그인 ID", example = "1234567890", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String oauth2Id();

    @Schema(description = "유저 회원 가입일 (UTC)", example = "2021-07-01T00:00:00Z", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Instant joinedDate();
}
