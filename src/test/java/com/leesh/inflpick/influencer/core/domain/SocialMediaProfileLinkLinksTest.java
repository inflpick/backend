package com.leesh.inflpick.influencer.core.domain;

import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SocialMediaProfileLinkLinksTest {

    @Test
    @DisplayName("유효한 값으로 SocialMediaLinks 생성되어야 한다.")
    void socialMediaGetImmutableCreationWithValidValues() throws URISyntaxException {
        SocialMediaProfileLink facebookLink = new SocialMediaProfileLink(SocialMediaPlatform.FACEBOOK, "https://www.facebook.com");
        SocialMediaProfileLink twitterLink = new SocialMediaProfileLink(SocialMediaPlatform.X, "https://www.twitter.com");
        SocialMediaProfileLinks socialMediaProfileLinks = new SocialMediaProfileLinks(Set.of(facebookLink, twitterLink));
        assertNotNull(socialMediaProfileLinks);
        assertEquals(2, socialMediaProfileLinks.size());
    }

    @Test
    @DisplayName("null 링크 리스트로 SocialMediaLinks 생성 시 예외가 발생해야 한다.")
    void socialMediaLinksCreationWithNullGetImmutableThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new SocialMediaProfileLinks(null);
        });
    }

    @Test
    @DisplayName("소셜 미디어 플랫폼으로 SocialMediaLink 가져오기")
    void getSocialMediaLinkByPlatform() throws URISyntaxException {
        SocialMediaProfileLink facebookLink = new SocialMediaProfileLink(SocialMediaPlatform.FACEBOOK, "https://www.facebook.com");
        SocialMediaProfileLink twitterLink = new SocialMediaProfileLink(SocialMediaPlatform.X, "https://www.twitter.com");
        SocialMediaProfileLinks socialMediaProfileLinks = new SocialMediaProfileLinks(Set.of(facebookLink, twitterLink));
        assertEquals(facebookLink, socialMediaProfileLinks.getSocialMediaLink(SocialMediaPlatform.FACEBOOK));
    }

    @Test
    @DisplayName("존재하지 않는 소셜 미디어 플랫폼으로 SocialMediaLink 가져오기 시 예외가 발생해야 한다.")
    void getSocialMediaLinkByNonExistentPlatformThrowsException() {
        SocialMediaProfileLink facebookLink = new SocialMediaProfileLink(SocialMediaPlatform.FACEBOOK, "https://www.facebook.com");
        SocialMediaProfileLinks socialMediaProfileLinks = new SocialMediaProfileLinks(Set.of(facebookLink));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> socialMediaProfileLinks.getSocialMediaLink(SocialMediaPlatform.X));
        assertEquals("url not found: X", exception.getMessage());
    }

    @Test
    @DisplayName("소셜 미디어 플랫폼 존재 여부 확인")
    void hasSocialMediaPlatform() throws URISyntaxException {
        SocialMediaProfileLink facebookLink = new SocialMediaProfileLink(SocialMediaPlatform.FACEBOOK, "https://www.facebook.com");
        SocialMediaProfileLink twitterLink = new SocialMediaProfileLink(SocialMediaPlatform.X, "https://www.twitter.com");
        SocialMediaProfileLinks socialMediaProfileLinks = new SocialMediaProfileLinks(Set.of(facebookLink, twitterLink));
        assertTrue(socialMediaProfileLinks.hasSocialMedia(SocialMediaPlatform.FACEBOOK));
        assertFalse(socialMediaProfileLinks.hasSocialMedia(SocialMediaPlatform.INSTAGRAM));
    }
}