package com.leesh.inflpick.v2.user.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.user.domain.vo.Oauth2Provider;
import com.leesh.inflpick.v2.user.domain.vo.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "유저 응답", description = "유저 응답")
public interface UserResponseDocs {

    @Schema(name = "id", description = "유저 ID", example = "67264864c67b2d08a657e2a9", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String id();

    @Schema(name = "nickname", description = "유저 닉네임", example = "inflpicker", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String nickname();

    @Schema(name = "profileImageUrl", description = "프로필 이미지 URL", example = "https://profile-image.com", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String profileImageUrl();

    @Schema(name = "email", description = "이메일", example = "inflpicker@gmail.com", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String email();

    @Schema(name = "role", description = "유저 권한", example = "USER", implementation = Role.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String role();

    @Schema(name = "oauth2Provider", description = "OAuth2 제공자", example = "GOOGLE", implementation = Oauth2Provider.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String oauth2Provider();

    @Schema(name = "oauth2Id", description = "OAuth2 ID", example = "1234567890", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String oauth2Id();

    @Schema(name = "joinedDate", description = "가입일", example = "2021-07-01T00:00:00Z", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Instant joinedDate();

}
