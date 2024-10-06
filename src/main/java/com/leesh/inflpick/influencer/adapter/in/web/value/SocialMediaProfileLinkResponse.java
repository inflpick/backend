package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "소셜 미디어 프로필")
public record SocialMediaProfileLinkResponse(
        @Schema(description = "플랫폼 이름", example = "INSTAGRAM", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String platform,
        @Schema(description = "소셜 미디어 프로필 URL (URL 형식의 문자열)", example = "https://instagram.com/jimjongkook", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String url) {
    public static SocialMediaProfileLinkResponse from(SocialMediaProfileLink socialMediaProfileLink) {
        return new SocialMediaProfileLinkResponse(
                socialMediaProfileLink.getPlatformName(),
                socialMediaProfileLink.getProfileUri());
    }
}
