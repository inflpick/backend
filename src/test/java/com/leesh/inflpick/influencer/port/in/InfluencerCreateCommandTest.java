package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.*;
import com.leesh.inflpick.mock.TestUuidHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InfluencerCreateCommandTest {

    @DisplayName("toEntity 메서드를 통해 Influencer로 변환할 수 있다.")
    @Test
    void toEntity() {
        // given
        InfluencerName name = new InfluencerName("John Doe");
        InfluencerIntroduction briefIntroduction = new InfluencerIntroduction("An influencer");
        InfluencerDescription description = new InfluencerDescription("An influencer");
        ProfileImage profileImage = new ProfileImage(URI.create("http://example.com/profile.jpg"));
        SocialMediaProfileLink twitterLink = new SocialMediaProfileLink(SocialMediaPlatform.X, URI.create("http://twitter.com/johndoe"));
        SocialMediaProfileLinks socialMediaProfileLinks = new SocialMediaProfileLinks(List.of(twitterLink));
        InfluencerCreateCommand command = new InfluencerCreateCommand(name,
                briefIntroduction,
                description,
                profileImage,
                new HashSet<>(),
                socialMediaProfileLinks);

        // when
        String uuid = "test-uuid";
        Influencer influencer = command.toEntity(new TestUuidHolder(uuid));

        // then
        assertThat(influencer.getUuid()).isEqualTo(uuid);
        assertThat(influencer.getName()).isEqualTo(name.name());
        assertThat(influencer.getDescription()).isEqualTo(description.description());
        assertThat(influencer.getProfileImage()).isEqualTo(profileImage.uri().toASCIIString());
        assertThat(influencer.getSocialMediaProfileLinks()).isEqualTo(socialMediaProfileLinks);
        assertThat(influencer.getSocialMediaProfileLinks().hasSocialMedia(SocialMediaPlatform.X)).isTrue();
        assertThat(influencer.getSocialMediaProfileLinks().getSocialMediaLink(SocialMediaPlatform.X)).isEqualTo(twitterLink);
    }

}