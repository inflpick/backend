package com.leesh.inflpick.influencer.core.domain;

import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SocialMediaProfileLinkTest {

    @Test
    @DisplayName("유효한 값으로 SocialMediaLink 생성되어야 한다.")
    void socialMediaLinkCreationWithValidValues() throws URISyntaxException {
        SocialMediaPlatform platform = SocialMediaPlatform.FACEBOOK;
        URI uri = new URI("https://www.facebook.com");
        SocialMediaProfileLink socialMediaProfileLink = new SocialMediaProfileLink(platform, uri);
        assertNotNull(socialMediaProfileLink);
        assertEquals(platform, socialMediaProfileLink.platform());
        assertEquals(uri, socialMediaProfileLink.profileLink());
    }

}