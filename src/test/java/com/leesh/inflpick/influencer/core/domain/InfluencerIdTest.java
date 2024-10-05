package com.leesh.inflpick.influencer.core.domain;

import com.leesh.inflpick.influencer.core.domain.value.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InfluencerTest {

    @Test
    @DisplayName("유효한 데이터로 인플루언서 생성")
    void influencerCreationWithValidData() {
        // given
        SocialMediaProfileLink twitterLink = new SocialMediaProfileLink(SocialMediaPlatform.X,"http://twitter.com/johndoe");

        // when
        Influencer influencer = Influencer.builder()
                .id("123")
                .name(new InfluencerName("John Doe"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage("http://example.com/profile1.jpg"))
                .socialMediaProfileLinks(new SocialMediaProfileLinks(Set.of(twitterLink)))
                .build();

        // then
        assertEquals("123", influencer.getId());
        assertEquals("John Doe", influencer.getName());
        assertEquals("An influencer", influencer.getDescription());
    }

    @Test
    @DisplayName("빈 소셜 미디어 링크로 인플루언서 생성")
    void influencerCreationWithEmptySocialMediaLinks() {
        // given

        // when
        Influencer influencer = Influencer.builder()
                .id("123")
                .name(new InfluencerName("John Doe"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage("http://example.com/profile1.jpg"))
                .socialMediaProfileLinks(new SocialMediaProfileLinks(Set.of()))
                .build();

        // then
        assertEquals(0, influencer.getSocialMediaProfileLinks().size());
    }

    @Test
    @DisplayName("동일한 ID로 생성된 두 인플루언서는 동일")
    void influencersWithSameIdAreEqual() {
        // given
        String id = "123";
        Influencer influencer1 = Influencer.builder()
                .id(id)
                .name(new InfluencerName("John Doe"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage("http://example.com/profile1.jpg"))
                .socialMediaProfileLinks(new SocialMediaProfileLinks(Set.of()))
                .build();
        Influencer influencer2 = Influencer.builder()
                .id(id)
                .name(new InfluencerName("Jane Doe"))
                .description(new InfluencerDescription("Another influencer"))
                .profileImage(new ProfileImage("http://example.com/profile1.jpg"))
                .socialMediaProfileLinks(new SocialMediaProfileLinks(Set.of()))
                .build();

        // when & then
        assertEquals(influencer1, influencer2);
        assertEquals(influencer1.hashCode(), influencer2.hashCode());
    }

    @Test
    @DisplayName("다른 ID로 생성된 두 인플루언서는 다름")
    void influencersWithDifferentIdAreNotEqual() {
        // given
        Influencer influencer1 = Influencer.builder()
                .id("123")
                .name(new InfluencerName("John Doe"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage("http://example.com/profile1.jpg"))
                .socialMediaProfileLinks(new SocialMediaProfileLinks(Set.of()))
                .build();
        Influencer influencer2 = Influencer.builder()
                .id("456")
                .name(new InfluencerName("Jane Doe"))
                .description(new InfluencerDescription("Another influencer"))
                .profileImage(new ProfileImage("http://example.com/profile1.jpg"))
                .socialMediaProfileLinks(new SocialMediaProfileLinks(Set.of()))
                .build();

        // when & then
        assertNotEquals(influencer1, influencer2);
        assertNotEquals(influencer1.hashCode(), influencer2.hashCode());
    }
}