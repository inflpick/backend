package com.leesh.inflpick.influencer.domain;

import com.leesh.inflpick.influencer.core.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InfluencerTest {

    @Test
    @DisplayName("유효한 데이터로 인플루언서 생성 - influencerCreationWithValidData")
    void influencerCreationWithValidData() throws URISyntaxException {

        SocialMediaLink twitterLink = new SocialMediaLink(SocialMediaPlatform.X, new URI("http://twitter.com/johndoe"));
        Influencer influencer = Influencer.builder()
                .id(new InfluencerId("123"))
                .name(new InfluencerName("John Doe"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage(new URI("http://example.com/profile.jpg")))
                .socialMediaLinks(new SocialMediaLinks(List.of(twitterLink)))
                .build();

        assertEquals("123", influencer.getId());
        assertEquals("John Doe", influencer.getName());
        assertEquals("An influencer", influencer.getDescription());
        assertEquals("http://example.com/profile.jpg", influencer.getProfileImage());
    }

    @Test
    @DisplayName("빈 소셜 미디어 링크로 인플루언서 생성 - influencerCreationWithEmptySocialMediaLinks")
    void influencerCreationWithEmptySocialMediaLinks() throws URISyntaxException {

        Influencer influencer = Influencer.builder()
                .id(new InfluencerId("123"))
                .name(new InfluencerName("John Doe"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage(new URI("http://example.com/profile.jpg")))
                .socialMediaLinks(new SocialMediaLinks(List.of()))
                .build();

        assertEquals(0, influencer.getSocialMediaLinks().size());
    }
}