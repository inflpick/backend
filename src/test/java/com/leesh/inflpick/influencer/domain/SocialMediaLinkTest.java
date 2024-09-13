package com.leesh.inflpick.influencer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("null 소셜 미디어 플랫폼으로 SocialMediaLink 생성 시 예외가 발생해야 한다.")
    void socialMediaLinkCreationWithNullPlatformThrowsException() throws URISyntaxException {
        URI uri = new URI("https://www.facebook.com");
        assertThrows(NullPointerException.class, () -> {
            new SocialMediaLink(null, uri);
        });
    }

    @Test
    @DisplayName("null URI로 SocialMediaLink 생성 시 예외가 발생해야 한다.")
    void socialMediaLinkCreationWithNullUriThrowsException() {
        SocialMediaPlatform platform = SocialMediaPlatform.FACEBOOK;
        assertThrows(NullPointerException.class, () -> {
            new SocialMediaLink(platform, null);
        });
    }

}