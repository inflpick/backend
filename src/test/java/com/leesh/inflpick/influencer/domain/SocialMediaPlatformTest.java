package com.leesh.inflpick.influencer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SocialMediaPlatformTest {

    @Test
    @DisplayName("유효한 URI로 SocialMediaPlatform 생성되어야 한다.")
    void socialMediaPlatformCreationWithValidUri() {
        assertEquals(URI.create("https://www.instagram.com"), SocialMediaPlatform.INSTAGRAM.getUri());
        assertEquals(URI.create("https://www.youtube.com"), SocialMediaPlatform.YOUTUBE.getUri());
        assertEquals(URI.create("https://www.tiktok.com"), SocialMediaPlatform.TIKTOK.getUri());
        assertEquals(URI.create("https://www.facebook.com"), SocialMediaPlatform.FACEBOOK.getUri());
        assertEquals(URI.create("https://www.twitter.com"), SocialMediaPlatform.TWITTER.getUri());
    }

    @Test
    @DisplayName("null URI로 SocialMediaPlatform 생성 시 예외가 발생해야 한다.")
    void socialMediaPlatformCreationWithNullUriThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SocialMediaPlatform invalidPlatform = SocialMediaPlatform.valueOf("INVALID");
        });
    }
}