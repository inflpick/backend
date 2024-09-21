package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;
import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.adapter.out.persistence.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.core.domain.*;
import com.leesh.inflpick.mock.TestUuidHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

class InfluencerControllerTest {

    private InfluencerController sut;
    private final UuidHolder uuidHolder = new TestUuidHolder("test-uuid");

    @BeforeEach
    void init() {
        // ServletRequestAttributes
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/influencers");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @DisplayName("InfluencerRequest 객체로 인플루언서를 생성하면, 201 Created 상태코드와 생성된 인플루언서의 UUID를 가진 URI를 반환한다.")
    @Test
    void create() {

        // given
        sut = InfluencerController.builder()
                .createService((command, multipartFile) -> command.toEntity(uuidHolder))
                .build();

        SocialMediaRequest socialMediaRequest = SocialMediaRequest.builder()
                .platform("INSTAGRAM")
                .profileLinkUri("http://instagram.com/jimjongkook")
                .build();

        InfluencerRequest request = InfluencerRequest.builder()
                .name("test-introduction")
                .introduction("test-introduction")
                .description("test-description")
                .profileImageUri("http://test.com")
                .socialMediaLinks(List.of(socialMediaRequest))
                .build();

        MultipartFile profileImage = new MockMultipartFile("profileImage", "test.jpg", "image/jpeg", "test".getBytes());

        // when
        ResponseEntity<Void> result = sut.create(request, profileImage);

        // then
        Assertions.assertThat(result.getStatusCode().value()).isEqualTo(201);
        Assertions.assertThat(result.getBody()).isNull();
        Assertions.assertThat(result.getHeaders().getLocation().toString()).isEqualTo("http://localhost/api/influencers/test-uuid");
    }

    @DisplayName("인플루언서 UUID로 인플루언서를 조회하면, 200 OK 상태코드와 인플루언서 정보를 반환한다.")
    @Test
    void read() {

        // given
        String uuid = "test-uuid";
        Influencer influencer = Influencer.builder()
                .uuid(uuid)
                .name(InfluencerName.from("test-name"))
                .introduction(InfluencerIntroduction.from("test-introduction"))
                .description(InfluencerDescription.from("test-description"))
                .profileImage(ProfileImage.of("http://test.com"))
                .keywords(Keywords.EMPTY)
                .socialMediaProfileLinks(
                        new SocialMediaProfileLinks(
                                List.of(SocialMediaProfileLink.of(SocialMediaPlatform.INSTAGRAM, "http://instagram.com/jimjongkook"))
                        )
                )
                .build();


        sut = InfluencerController.builder()
                .readService(uuid1 -> influencer)
                .build();

        // when
        ResponseEntity<InfluencerResponse> result = sut.read(uuid);

        // then
        Assertions.assertThat(result.getStatusCode().value()).isEqualTo(200);
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().uuid()).isEqualTo(uuid);
        Assertions.assertThat(result.getBody().name()).isEqualTo("test-name");
        Assertions.assertThat(result.getBody().introduction()).isEqualTo("test-introduction");
        Assertions.assertThat(result.getBody().description()).isEqualTo("test-description");
        Assertions.assertThat(result.getBody().keywords()).hasSize(0);
        Assertions.assertThat(result.getBody().socialMediaLinks()).hasSize(1);
        Assertions.assertThat(result.getBody().socialMediaLinks()).isNotEmpty();
        Assertions.assertThat(result.getBody().socialMediaLinks().getFirst().platform()).isEqualTo("INSTAGRAM");
        Assertions.assertThat(result.getBody().socialMediaLinks().getFirst().profileLinkUri()).isEqualTo("http://instagram.com/jimjongkook");

    }

    @DisplayName("존재하지 않는 UUID로 인플루언서를 조회하면, InfluencerNotFoundException 예외가 발생한다.")
    @Test
    void read_notFound() {

        // given
        String uuid = "not-found-uuid";

        sut = InfluencerController.builder()
                .readService(uuid1 -> {
                    throw new InfluencerNotFoundException(uuid);
                })
                .build();

        // when
        Assertions.assertThatThrownBy(() -> sut.read(uuid))
                .isInstanceOf(InfluencerNotFoundException.class)
                .hasMessage(ResourceNotFoundException.MESSAGE_FORMAT.format(new Object[]{uuid, "인플루언서"}));
    }

}