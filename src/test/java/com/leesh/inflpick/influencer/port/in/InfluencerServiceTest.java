package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.*;
import com.leesh.inflpick.influencer.core.domain.value.*;
import com.leesh.inflpick.influencer.core.service.InfluencerServiceImpl;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.influencer.port.out.StorageService;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.mock.FakeInfluencerRepository;
import com.leesh.inflpick.mock.FakeKeywordRepository;
import com.leesh.inflpick.mock.FakeStorageService;
import com.leesh.inflpick.mock.TestUuidHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.net.URI;
import java.util.HashSet;
import java.util.List;

class InfluencerServiceTest {

    private final InfluencerRepository repository = new FakeInfluencerRepository();
    private final KeywordRepository keywordRepository = new FakeKeywordRepository();
    private final StorageService storageService = new FakeStorageService();

    @BeforeEach
    void init() {

        Influencer testInfluencer1 = Influencer.builder()
                .uuid("1")
                .name(new InfluencerName("John Doe"))
                .introduction(new InfluencerIntroduction("An influencer"))
                .description(new InfluencerDescription("An influencer"))
                .profileImage(new ProfileImage("http://example.com/profile1.jpg"))
                .keywords(Keywords.EMPTY)
                .socialMediaProfileLinks(new SocialMediaProfileLinks(List.of(
                        new SocialMediaProfileLink(SocialMediaPlatform.X, URI.create("http://twitter.com/johndoe"))
                )))
                .build();

        Influencer testInfluencer2 = Influencer.builder()
                .uuid("2")
                .name(new InfluencerName("Jane Doe"))
                .introduction(new InfluencerIntroduction("Another influencer"))
                .description(new InfluencerDescription("Another influencer"))
                .profileImage(new ProfileImage("http://example.com/profile2.jpg"))
                .keywords(Keywords.EMPTY)
                .socialMediaProfileLinks(new SocialMediaProfileLinks(List.of(
                        new SocialMediaProfileLink(SocialMediaPlatform.X, URI.create("http://twitter.com/janedoe"))
                )))
                .build();

        repository.save(testInfluencer1);
        repository.save(testInfluencer2);
    }

    @DisplayName("InfluencerCreateCommand 객체 인플루언서 생성을 하면, 존재하지 않는 키워드는 생성되지 않고 무시되며, 프로필 이미지가 등록된 후, 인플루언서가 생성된다.")
    @Test
    void createInfluencer() {

        // given
        InfluencerServiceImpl sut = InfluencerServiceImpl.builder()
                .uuidHolder(new TestUuidHolder("test-uuid"))
                .influencerRepository(repository)
                .keywordRepository(keywordRepository)
                .storageService(storageService)
                .build();

        InfluencerName name = new InfluencerName("John Doe");
        InfluencerIntroduction introduction = new InfluencerIntroduction("An influencer");
        InfluencerDescription description = new InfluencerDescription("An influencer");
        ProfileImage profileImage = new ProfileImage("http://example.com/profile1.jpg");
        SocialMediaProfileLink twitterLink = new SocialMediaProfileLink(SocialMediaPlatform.X, URI.create("http://twitter.com/johndoe"));
        SocialMediaProfileLinks socialMediaProfileLinks = new SocialMediaProfileLinks(List.of(twitterLink));
        InfluencerCreateCommand command = new InfluencerCreateCommand(name,
                introduction,
                description,
                profileImage,
                new HashSet<>(List.of("1")),
                socialMediaProfileLinks);
        long count = repository.count();

        // when
        MockMultipartFile profileImageFile = new MockMultipartFile("profileImage", "test-image.jpg", "image/jpeg", "test-image".getBytes());
        sut.create(command, profileImageFile);

        // then
        Assertions.assertThat(repository.count()).isEqualTo(count + 1);
    }

}