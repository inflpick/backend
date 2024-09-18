package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLink;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "소셜 미디어 링크")
public record SocialMediaResponse(
        @Schema(description = "플랫폼 이름", example = "INSTAGRAM", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String platform,
        @Schema(description = "소셜 미디어 프로필 링크 URI (URI 형식의 문자열)", example = "https://instagram.com/jimjongkook", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String profileLinkUri) {
    public static SocialMediaResponse from(SocialMediaProfileLink socialMediaProfileLink) {
        return new SocialMediaResponse(
                socialMediaProfileLink.getPlatformName(),
                socialMediaProfileLink.getProfileUri());
    }
}
