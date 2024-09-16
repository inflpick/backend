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

    private InfluencerService sut;
    private final InfluencerRepository repository = new FakeInfluencerRepository();

    @BeforeEach
    void init() {

        Influencer testInfluencer1 = Influencer.builder()
                .id(new InfluencerId("1"))
                .name(new InfluencerName("John Doe"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage(URI.create("http://example.com/profile.jpg")))
                .socialMediaLinks(new SocialMediaLinks(List.of(
                        new SocialMediaLink(SocialMediaPlatform.X, URI.create("http://twitter.com/johndoe"))
                )))
                .build();
        Influencer testInfluencer2 = Influencer.builder()
                .id(new InfluencerId("2"))
                .name(new InfluencerName("Jane Doe"))
                .description(new InfluencerDescription("Another influencer"))
                .profileImage(new ProfileImage(URI.create("http://example.com/profile2.jpg")))
                .socialMediaLinks(new SocialMediaLinks(List.of(
                        new SocialMediaLink(SocialMediaPlatform.X, URI.create("http://twitter.com/janedoe"))
                )))
                .build();

        repository.save(testInfluencer1);
        repository.save(testInfluencer2);

        this.sut = new InfluencerServiceImpl(
                new TestUuidHolder("test-uuid"),
                repository
        );
    }

    @DisplayName("인플루언서 생성에 성공하면, 크기가 1 증가한다.")
    @Test
    void createInfluencer() {

        // given
        InfluencerName name = new InfluencerName("John Doe");
        InfluencerDescription description = new InfluencerDescription("An influencer");
        ProfileImage profileImage = new ProfileImage(URI.create("http://example.com/profile.jpg"));
        SocialMediaLink twitterLink = new SocialMediaLink(SocialMediaPlatform.X, URI.create("http://twitter.com/johndoe"));
        SocialMediaLinks socialMediaLinks = new SocialMediaLinks(List.of(twitterLink));
        long count = repository.count();

        // when
        sut.create(name, description, profileImage, socialMediaLinks);

        // then
        Assertions.assertThat(repository.count()).isEqualTo(count + 1);
    }

    @DisplayName("ID를 통해서 인플루언서를 조회할 수 있어야 한다.")
    @Test
    void getById() {
        // given
        String uuid = "1";
        InfluencerId id = new InfluencerId(uuid);

        // when
        Influencer influencer = sut.getById(id);

        // then
        Assertions.assertThat(influencer).isNotNull();
        Assertions.assertThat(influencer.getId()).isEqualTo(id.id());
    }

}