package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.influencer.core.domain.SocialMediaLink;
import com.leesh.inflpick.influencer.core.domain.SocialMediaPlatform;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;

@Schema(name = "소셜 미디어 링크")
public record SocialMediaRequest(
        @Schema(description = "소셜 미디어 플랫폼", example = "INSTAGRAM", implementation = SocialMediaPlatform.class, requiredMode = Schema.RequiredMode.REQUIRED)
        @Nullable
        String platform,
        @Schema(description = "소셜 미디어 프로필 링크 URI (URI 형식의 문자열)", example = "https://instagram.com/jimjongkook", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        @Nullable
        String profileLinkUri) {

    public @NotNull SocialMediaLink toEntity() throws UserInputException {
        SocialMediaPlatform validatedSocialMediaPlatform = validateSocialMediaPlatformInput(platform);
        URI validatedProfileLinkUri = validateProfileLinkUriInput(profileLinkUri);
        return new SocialMediaLink(validatedSocialMediaPlatform, validatedProfileLinkUri);
    }

    private URI validateProfileLinkUriInput(@Nullable String profileLinkUri) {
        if (profileLinkUri == null || profileLinkUri.isBlank()) {
            throw new UserInputException("소셜 미디어 프로필 링크 주소를 입력해주세요.", "");
        }
        try {
            return new URI(profileLinkUri);
        } catch (Exception e) {
            throw new UserInputException("올바른 URI 형식이 아닙니다.", profileLinkUri);
        }
    }

    private SocialMediaPlatform validateSocialMediaPlatformInput(@Nullable String platform) {
        if (platform == null || platform.isBlank()) {
            throw new UserInputException("소셜 미디어 플랫폼을 입력해주세요.", "");
        }
        try {
            return SocialMediaPlatform.valueOf(platform);
        } catch (IllegalArgumentException e) {
            String availablePlatforms = String.join(", ", SocialMediaPlatform.availablePlatforms());
            throw new UserInputException("소셜 미디어 플랫폼은 [%s] 중에 하나여야 합니다.".formatted(availablePlatforms), platform);
        }
    }

}
