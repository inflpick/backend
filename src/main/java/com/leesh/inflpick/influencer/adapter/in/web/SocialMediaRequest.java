package com.leesh.inflpick.influencer.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leesh.inflpick.common.adapter.in.web.MissingRequiredFieldsException;
import com.leesh.inflpick.influencer.core.domain.SocialMediaPlatform;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLink;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Schema(name = "소셜 미디어 요청", description = "소셜 미디어 생성 요청 필드")
@Builder
public record SocialMediaRequest(
        @Schema(description = "소셜 미디어 플랫폼", example = "INSTAGRAM", implementation = SocialMediaPlatform.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String platform,
        @Schema(description = "소셜 미디어 프로필 링크 URI (URI 형식의 문자열)", example = "https://instagram.com/jimjongkook", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String profileLinkUri) {

    @JsonCreator
    public SocialMediaRequest(@JsonProperty("platform") @Nullable String platform,
                              @JsonProperty("profileLinkUri") @Nullable String profileLinkUri) {
        validateRequireFields(platform, profileLinkUri);
        this.platform = platform.strip();
        assert profileLinkUri != null;
        this.profileLinkUri = profileLinkUri.strip();
    }

    private void validateRequireFields(@Nullable String platform,
                                       @Nullable String profileLinkUri) {
        if (platform == null || platform.isBlank()) {
            throw new MissingRequiredFieldsException("소셜 미디어 플랫폼을 입력해주세요.");
        }
        if (profileLinkUri == null || profileLinkUri.isBlank()) {
            throw new MissingRequiredFieldsException("소셜 미디어 프로필 링크 주소를 입력해주세요.");
        }
    }

    public @NotNull SocialMediaProfileLink toEntity() {
        SocialMediaPlatform validatedSocialMediaPlatform = validateAndCreateSocialMediaPlatformInput(platform);
        return SocialMediaProfileLink.of(validatedSocialMediaPlatform, profileLinkUri);
    }

    private SocialMediaPlatform validateAndCreateSocialMediaPlatformInput(@NotNull String platform) {
        try {
            return SocialMediaPlatform.valueOf(platform);
        } catch (IllegalArgumentException e) {
            throw new InvalidSocialMediaPlatformException(platform);
        }
    }

}
