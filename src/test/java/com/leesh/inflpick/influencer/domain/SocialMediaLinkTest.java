package com.leesh.inflpick.influencer.domain;

import com.leesh.inflpick.influencer.core.domain.SocialMediaLink;
import com.leesh.inflpick.influencer.core.domain.SocialMediaPlatform;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SocialMediaLinkTest {

    @Test
    @DisplayName("유효한 값으로 SocialMediaLink 생성되어야 한다.")
    void socialMediaLinkCreationWithValidValues() throws URISyntaxException {
        SocialMediaPlatform platform = SocialMediaPlatform.FACEBOOK;
        URI uri = new URI("https://www.facebook.com");
        SocialMediaLink socialMediaLink = new SocialMediaLink(platform, uri);
        assertNotNull(socialMediaLink);
        assertEquals(platform, socialMediaLink.platform());
        assertEquals(uri, socialMediaLink.uri());
    }

}