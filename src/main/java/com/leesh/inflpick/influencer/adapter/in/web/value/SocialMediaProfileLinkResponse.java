package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.influencer.adapter.in.web.docs.SocialMediaProfileLinkResponseApiDocs;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;

public record SocialMediaProfileLinkResponse(String platform, String url) implements SocialMediaProfileLinkResponseApiDocs {
    public static SocialMediaProfileLinkResponse from(SocialMediaProfileLink socialMediaProfileLink) {
        return new SocialMediaProfileLinkResponse(
                socialMediaProfileLink.getPlatformName(),
                socialMediaProfileLink.getProfileUri());
    }
}
