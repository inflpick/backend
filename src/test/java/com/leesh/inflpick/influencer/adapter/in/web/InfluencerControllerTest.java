package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.mock.TestUuidHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

class InfluencerControllerTest {

    private InfluencerController sut;
    private final UuidHolder uuidHolder = new TestUuidHolder("test-uuid");

    @BeforeEach
    void init() {
        sut = InfluencerController.builder()
                .createService(command -> command.toEntity(uuidHolder))
                .build();

        // Mock the HttpServletRequest and set it in the RequestContextHolder
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);
    }

    @DisplayName("인플루언서를 생성할 수 있다.")
    @Test
    void create() {

        // given
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

        // when
        ResponseEntity<Void> result = sut.create(request);

        // then
        Assertions.assertThat(result.getStatusCode().value()).isEqualTo(201);
        Assertions.assertThat(result.getBody()).isNull();
    }


}