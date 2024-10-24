package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.v2.influencer.adapter.in.web.SocialMediaProfileLinkResponseDocs;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;

public record SocialMediaProfileLinkResponse(String platform, String url) implements SocialMediaProfileLinkResponseDocs {
    public static SocialMediaProfileLinkResponse from(SocialMediaProfileLink socialMediaProfileLink) {
        return new SocialMediaProfileLinkResponse(
                socialMediaProfileLink.getPlatformName(),
                socialMediaProfileLink.getProfileUri());
    }
}
