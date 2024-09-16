package com.leesh.inflpick.influencer.domain;

import com.leesh.inflpick.influencer.core.domain.SocialMediaLink;
import com.leesh.inflpick.influencer.core.domain.SocialMediaLinks;
import com.leesh.inflpick.influencer.core.domain.SocialMediaPlatform;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SocialMediaLinksTest {

    @Test
    @DisplayName("유효한 값으로 SocialMediaLinks 생성되어야 한다.")
    void socialMediaGetImmutableCreationWithValidValues() throws URISyntaxException {
        SocialMediaLink facebookLink = new SocialMediaLink(SocialMediaPlatform.FACEBOOK, new URI("https://www.facebook.com"));
        SocialMediaLink twitterLink = new SocialMediaLink(SocialMediaPlatform.X, new URI("https://www.twitter.com"));
        SocialMediaLinks socialMediaLinks = new SocialMediaLinks(List.of(facebookLink, twitterLink));
        assertNotNull(socialMediaLinks);
        assertEquals(2, socialMediaLinks.size());
    }

    @Test
    @DisplayName("null 링크 리스트로 SocialMediaLinks 생성 시 예외가 발생해야 한다.")
    void socialMediaLinksCreationWithNullGetImmutableThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new SocialMediaLinks(null);
        });
    }

    @Test
    @DisplayName("소셜 미디어 플랫폼으로 SocialMediaLink 가져오기")
    void getSocialMediaLinkByPlatform() throws URISyntaxException {
        SocialMediaLink facebookLink = new SocialMediaLink(SocialMediaPlatform.FACEBOOK, new URI("https://www.facebook.com"));
        SocialMediaLink twitterLink = new SocialMediaLink(SocialMediaPlatform.X, new URI("https://www.twitter.com"));
        SocialMediaLinks socialMediaLinks = new SocialMediaLinks(List.of(facebookLink, twitterLink));
        assertEquals(facebookLink, socialMediaLinks.getSocialMediaLink(SocialMediaPlatform.FACEBOOK));
    }

    @Test
    @DisplayName("존재하지 않는 소셜 미디어 플랫폼으로 SocialMediaLink 가져오기 시 예외가 발생해야 한다.")
    void getSocialMediaLinkByNonExistentPlatformThrowsException() throws URISyntaxException {
        SocialMediaLink facebookLink = new SocialMediaLink(SocialMediaPlatform.FACEBOOK, new URI("https://www.facebook.com"));
        SocialMediaLinks socialMediaLinks = new SocialMediaLinks(List.of(facebookLink));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            socialMediaLinks.getSocialMediaLink(SocialMediaPlatform.X);
        });
        assertEquals("link not found: X", exception.getMessage());
    }

    @Test
    @DisplayName("소셜 미디어 플랫폼 존재 여부 확인")
    void hasSocialMediaPlatform() throws URISyntaxException {
        SocialMediaLink facebookLink = new SocialMediaLink(SocialMediaPlatform.FACEBOOK, new URI("https://www.facebook.com"));
        SocialMediaLink twitterLink = new SocialMediaLink(SocialMediaPlatform.X, new URI("https://www.twitter.com"));
        SocialMediaLinks socialMediaLinks = new SocialMediaLinks(List.of(facebookLink, twitterLink));
        assertTrue(socialMediaLinks.hasSocialMedia(SocialMediaPlatform.FACEBOOK));
        assertFalse(socialMediaLinks.hasSocialMedia(SocialMediaPlatform.INSTAGRAM));
    }
}