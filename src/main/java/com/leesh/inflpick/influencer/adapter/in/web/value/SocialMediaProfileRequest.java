package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;
import com.leesh.inflpick.v2.influencer.adapter.in.web.SocialMediaProfileRequestDocs;
import com.leesh.inflpick.v2.influencer.domain.exception.NotSupportSnsPlatformException;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsPlatform;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;
import lombok.Builder;

@Builder
public record SocialMediaProfileRequest(String platform, String uri) implements SocialMediaProfileRequestDocs {

    @JsonCreator
    public SocialMediaProfileRequest(String platform, String uri) {
        RequiredFieldsValidator.validate(platform, uri);
        assert platform != null;
        this.platform = platform.strip();
        assert uri != null;
        this.uri = uri.strip();
    }

    public SnsProfileLink toEntity() {
        SnsPlatform platform;
        try {
            platform = SnsPlatform.valueOf(this.platform);
        } catch (IllegalArgumentException e) {
            throw new NotSupportSnsPlatformException("Not support sns platform: " + this.platform);
        }
        return SnsProfileLink.create(platform, uri);
    }
}
