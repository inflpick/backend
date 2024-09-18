package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.*;
import com.leesh.inflpick.influencer.core.service.InfluencerServiceImpl;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.mock.FakeInfluencerRepository;
import com.leesh.inflpick.mock.TestUuidHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;

class InfluencerServiceTest {

    private InfluencerServiceImpl sut;
    private final InfluencerRepository repository = new FakeInfluencerRepository();

    @BeforeEach
    void init() {

        Influencer testInfluencer1 = Influencer.builder()
                .uuid("1")
                .name(new InfluencerName("John Doe"))
                .introduction(new InfluencerIntroduction("An influencer"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage(URI.create("http://example.com/profile.jpg")))
                .socialMediaProfileLinks(new SocialMediaProfileLinks(List.of(
                        new SocialMediaProfileLink(SocialMediaPlatform.X, URI.create("http://twitter.com/johndoe"))
                )))
                .build();
        Influencer testInfluencer2 = Influencer.builder()
                .uuid("2")
                .name(new InfluencerName("Jane Doe"))
                .introduction(new InfluencerIntroduction("Another influencer"))
                .description(new InfluencerDescription("Another influencer"))
                .profileImage(new ProfileImage(URI.create("http://example.com/profile2.jpg")))
                .socialMediaProfileLinks(new SocialMediaProfileLinks(List.of(
                        new SocialMediaProfileLink(SocialMediaPlatform.X, URI.create("http://twitter.com/janedoe"))
                )))
                .build();

        repository.save(testInfluencer1);
        repository.save(testInfluencer2);

        this.sut = InfluencerServiceImpl.builder()
                .uuidHolder(new TestUuidHolder("test-uuid"))
                .influencerRepository(repository)
                .build();
    }

    @DisplayName("InfluencerCreateCommand 객체로 인플루언서 생성에 성공하면, 크기가 1 증가한다.")
    @Test
    void createInfluencer() {

        // given
        InfluencerName name = new InfluencerName("John Doe");
        InfluencerIntroduction introduction = new InfluencerIntroduction("An influencer");
        InfluencerDescription description = new InfluencerDescription("An influencer");
        ProfileImage profileImage = new ProfileImage(URI.create("http://example.com/profile.jpg"));
        SocialMediaProfileLink twitterLink = new SocialMediaProfileLink(SocialMediaPlatform.X, URI.create("http://twitter.com/johndoe"));
        SocialMediaProfileLinks socialMediaProfileLinks = new SocialMediaProfileLinks(List.of(twitterLink));
        InfluencerCreateCommand command = new InfluencerCreateCommand(name,
                introduction,
                description,
                profileImage,
                socialMediaProfileLinks);
        long count = repository.count();

        // when
        sut.create(command);

        // then
        Assertions.assertThat(repository.count()).isEqualTo(count + 1);
    }

}