package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import com.leesh.inflpick.common.adapter.out.time.InstantHolder;
import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerRequest;
import com.leesh.inflpick.influencer.adapter.in.web.value.SocialMediaRequest;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.influencer.core.domain.value.*;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateCommand;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateService;
import com.leesh.inflpick.influencer.port.in.InfluencerReadService;
import com.leesh.inflpick.mock.TestInstantHolder;
import com.leesh.inflpick.mock.TestUuidHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InfluencerController.class)
class InfluencerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private UuidHolder uuidHolder = new TestUuidHolder("test-uuid");
    @MockBean
    private InfluencerCreateService createService;
    @MockBean
    private InfluencerReadService readService;

    @BeforeEach
    void init() {

    }

    @DisplayName("인플루언서 생성 API 요청 시, 정상 입력 값을 입력하면, 201 Created 상태코드와 생성된 리소스 URI를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideValidJsonRequests")
    void create(String jsonRequest) throws Exception {

        // given
        String apiPath = "/api/influencers";
        String uriString = UriComponentsBuilder.fromHttpUrl("http://localhost").path(apiPath).toUriString();
        MockMultipartFile profileImage = new MockMultipartFile(
                "profileImage",
                "test.jpg",
                "image/jpeg",
                "test".getBytes());
        MockMultipartFile requestFile = new MockMultipartFile("request",
                "",
                "application/json",
                jsonRequest.getBytes());

        // when & then
        Mockito.when(createService.create(
                        Mockito.any(InfluencerCreateCommand.class),
                        Mockito.any(MultipartFile.class)))
                .thenReturn(uuidHolder.uuid());

        mockMvc.perform(multipart(apiPath)
                        .file(profileImage)
                        .file(requestFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(header().string(HttpHeaders.LOCATION, uriString + "/" + uuidHolder.uuid()))
                .andDo(print())
        ;
    }

    private static Stream<String> provideValidJsonRequests() {
        return Stream.of(
        """
                {
                    "name": "test-name",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "keywords": [],
                    "profileImageUri": "http://test.com",
                    "socialMediaLinks": []
                }
                """,
                """
                {
                    "name": "test-name",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "profileImageUri": "http://test.com",
                    "socialMediaLinks": []
                }
                """,
                """
                {
                    "name": "test-name",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "profileImageUri": "http://test.com",
                    "keywords": [],
                    "socialMediaLinks": []
                }
                """,
                """
                {
                    "name": "test-name",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "profileImageUri": "http://test.com",
                    "keywords": [],
                    "socialMediaLinks": []
                }
                """
        );
    }

    @DisplayName("인플루언서 생성 API 요청 시, 필수 입력 값을 입력하지 않으면, 400 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInvalidJsonRequests")
    void create_missingRequiredFields(String jsonRequest) throws Exception {

        // given
        Instant now = Instant.now();
        InstantHolder instantHolder = new TestInstantHolder(now);
        MockMultipartFile profileImage = new MockMultipartFile("profileImage", "test.jpg", "image/jpeg", "test".getBytes());
        CommonApiErrorCode apiErrorCode = CommonApiErrorCode.MISSING_REQUIRED_FIELDS;
        String apiPath = "/api/influencers";
        MockMultipartFile request = new MockMultipartFile("request", "", "application/json", jsonRequest.getBytes());
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.of(instantHolder, apiErrorCode, HttpMethod.POST.name(), apiPath);

        // when & then
        mockMvc.perform(multipart(apiPath)
                        .file(profileImage)
                        .file(request)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(header().doesNotExist(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.status").value(apiErrorResponse.status()))
                .andExpect(jsonPath("$.code").value(apiErrorResponse.code()))
                .andExpect(jsonPath("$.reason").value(apiErrorResponse.reason()))
                .andExpect(jsonPath("$.action").value(apiErrorResponse.action()))
                .andExpect(jsonPath("$.method").value(apiErrorResponse.method()))
                .andExpect(jsonPath("$.path").value(apiErrorResponse.path()))
                .andDo(print())
        ;
    }

    private static Stream<String> provideInvalidJsonRequests() {
        return Stream.of(
                """
                        {
                            "introduction": "test-introduction",
                            "description": "test-description",
                            "profileImageUri": "http://test.com",
                            "socialMediaLinks": []
                        }
                        """,
                        """
                        {
                            "name": "test-name",
                            "description": "test-description",
                            "profileImageUri": "http://test.com",
                            "socialMediaLinks": []
                        }
                        """,
                        """
                        {
                            "name": "test-name",
                            "introduction": "test-introduction",
                            "profileImageUri": "http://test.com",
                            "socialMediaLinks": []
                        }
                        """,
                        """
                        {
                            "name": "test-name",
                            "introduction": "test-introduction",
                            "description": "test-description",
                            "socialMediaLinks": []
                        }
                        """
        );
    }

    @DisplayName("인플루언서 이름을 1~50자 이내로 입력하지 않으면, 400 응답을 반환하고, 이에 맞는 에러 메세지를 반환한다.")
    @Test
    void create_invalidName() {

        // given
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        String apiPath = "/api/influencers";
        String httpMethod = HttpMethod.POST.name();
        mockHttpServletRequest.setRequestURI(apiPath);
        mockHttpServletRequest.setMethod(httpMethod);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        SocialMediaRequest socialMediaRequest = SocialMediaRequest.builder()
                .platform("INSTAGRAM")
                .profileLinkUri("http://instagram.com/jimjongkook")
                .build();

        InfluencerRequest request = InfluencerRequest.builder()
                .name("")
                .introduction("test-introduction")
                .description("test-description")
                .profileImageUri("http://test.com")
                .socialMediaLinks(List.of(socialMediaRequest))
                .build();

        MultipartFile profileImage = new MockMultipartFile("profileImage", "test.jpg", "image/jpeg", "test".getBytes());

        // when
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


    }

    @DisplayName("존재하지 않는 UUID로 인플루언서를 조회하면, InfluencerNotFoundException 예외가 발생한다.")
    @Test
    void read_notFound() {

        // given
        String uuid = "not-found-uuid";

    }

}