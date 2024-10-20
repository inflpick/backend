package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;
import com.leesh.inflpick.influencer.adapter.in.web.docs.SocialMediaProfileRequestApiDocs;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Builder
public record SocialMediaProfileRequest(String platform, String uri) implements SocialMediaProfileRequestApiDocs {

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
