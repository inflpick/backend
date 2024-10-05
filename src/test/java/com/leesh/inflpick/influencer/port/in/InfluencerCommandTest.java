package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.influencer.core.domain.value.*;
import com.leesh.inflpick.influencer.port.InfluencerCommand;
import com.leesh.inflpick.mock.TestUuidHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InfluencerCommandTest {

    @DisplayName("toEntity 메서드를 통해 Influencer로 변환할 수 있다.")
    @Test
    void toEntity() {
        // given
        InfluencerName name = new InfluencerName("John Doe");
        InfluencerIntroduction briefIntroduction = new InfluencerIntroduction("An influencer");
        InfluencerDescription description = new InfluencerDescription("An influencer");
        SocialMediaProfileLink twitterLink = new SocialMediaProfileLink(SocialMediaPlatform.X, "http://twitter.com/johndoe");
        SocialMediaProfileLinks socialMediaProfileLinks = new SocialMediaProfileLinks(Set.of(twitterLink));
        InfluencerCommand command = new InfluencerCommand(name,
                briefIntroduction,
                description,
                new HashSet<>(),
                socialMediaProfileLinks);

        // when
        String uuid = "test-id";
        Influencer influencer = command.toEntity(new TestUuidHolder(uuid));

        // then
        assertThat(influencer.getId()).isEqualTo(uuid);
        assertThat(influencer.getName()).isEqualTo(name.name());
        assertThat(influencer.getDescription()).isEqualTo(description.description());
        assertThat(influencer.getSocialMediaProfileLinks()).isEqualTo(socialMediaProfileLinks);
        assertThat(influencer.getSocialMediaProfileLinks().hasSocialMedia(SocialMediaPlatform.X)).isTrue();
        assertThat(influencer.getSocialMediaProfileLinks().getSocialMediaLink(SocialMediaPlatform.X)).isEqualTo(twitterLink);
    }

}