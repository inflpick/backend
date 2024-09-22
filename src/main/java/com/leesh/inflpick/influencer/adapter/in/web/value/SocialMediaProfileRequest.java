package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leesh.inflpick.common.adapter.in.web.RequiredFieldsValidator;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Schema(name = "소셜 미디어 요청", description = "소셜 미디어 생성 요청 필드")
@Builder
public record SocialMediaProfileRequest(
        @Schema(description = "소셜 미디어 플랫폼", example = "INSTAGRAM", implementation = SocialMediaPlatform.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String platform,
        @Schema(description = "소셜 미디어 프로필 URI (URI 형식의 문자열)", example = "https://instagram.com/jimjongkook", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String uri) {

    @JsonCreator
    public SocialMediaProfileRequest(@JsonProperty("platform") @Nullable String platform,
                                     @JsonProperty("uri") @Nullable String uri) {
        RequiredFieldsValidator.validate(platform, uri);
        assert platform != null;
        this.platform = platform.strip();
        assert uri != null;
        this.uri = uri.strip();
    }

    public @NotNull SocialMediaProfileLink toEntity() {
        SocialMediaPlatform socialMediaPlatform = SocialMediaPlatform.from(platform);
        return SocialMediaProfileLink.of(
                socialMediaPlatform,
                uri);
    }
}
