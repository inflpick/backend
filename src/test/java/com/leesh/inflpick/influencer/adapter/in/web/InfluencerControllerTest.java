package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import com.leesh.inflpick.common.adapter.out.time.InstantHolder;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.influencer.core.domain.value.*;
import com.leesh.inflpick.influencer.port.InfluencerCommand;
import com.leesh.inflpick.influencer.port.in.InfluencerCommandService;
import com.leesh.inflpick.influencer.port.in.InfluencerQueryService;
import com.leesh.inflpick.influencer.port.out.InfluencerNotFoundException;
import com.leesh.inflpick.mock.TestInstantHolder;
import com.leesh.inflpick.mock.TestUuidHolder;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import com.leesh.inflpick.review.port.in.ReviewQueryService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InfluencerController.class)
class InfluencerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final UuidHolder uuidHolder = new TestUuidHolder("test-uuid");
    @MockBean
    private InfluencerCommandService commandService;
    @MockBean
    private InfluencerQueryService queryService;
    @MockBean
    private ProductQueryService productQueryService;
    @MockBean
    private ReviewQueryService reviewQueryService;
    @MockBean
    private StorageService storageService;

    @DisplayName("인플루언서 생성 API 요청 시, 정상 입력 값을 입력하면, 201 Created 상태코드와 생성된 리소스 URI를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideValidJsonRequests")
    void create(String jsonRequest) throws Exception {

        // given
        String apiPath = "/influencers";
        String uriString = UriComponentsBuilder.fromHttpUrl("http://localhost").path(apiPath).toUriString();
        MockMultipartFile requestFile = new MockMultipartFile("request",
                "",
                "application/json",
                jsonRequest.getBytes());

        // when & then
        Mockito.when(commandService.create(Mockito.any(InfluencerCommand.class)))
                .thenReturn(uuidHolder.uuid());

        mockMvc.perform(post(apiPath)
                        .content(requestFile.getBytes())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string(HttpHeaders.LOCATION, uriString + "/" + uuidHolder.uuid()))
                .andDo(print());
    }

    private static Stream<String> provideValidJsonRequests() {
        return Stream.of(
        """
                {
                    "nickname": "test-influencer-nickname",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "keywords": [],
                    "profileImageUri": "http://test.com",
                    "socialMediaProfileLinks": []
                }
                """,
                """
                {
                    "nickname": "test-influencer-nickname",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "profileImageUri": "http://test.com",
                    "socialMediaProfileLinks": []
                }
                """,
                """
                {
                    "nickname": "test-influencer-nickname",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "profileImageUri": "http://test.com",
                    "keywords": [],
                    "socialMediaProfileLinks": []
                }
                """,
                """
                {
                    "nickname": "test-influencer-nickname",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "profileImageUri": "http://test.com",
                    "keywords": [],
                    "socialMediaProfileLinks": []
                }
                """
        );
    }

    @DisplayName("인플루언서 생성 API 요청 시, 필수 입력 값을 입력하지 않으면, 400 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInvalidJsonRequests")
    void create_missingRequiredFields(String jsonRequest) throws Exception {

        // given
        String apiPath = "/influencers";
        MockMultipartFile request = new MockMultipartFile("request", "", "application/json", jsonRequest.getBytes());
        Instant now = Instant.now();
        InstantHolder instantHolder = new TestInstantHolder(now);
        CommonApiErrorCode apiErrorCode = CommonApiErrorCode.MISSING_REQUIRED_FIELDS;
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.of(instantHolder, apiErrorCode, HttpMethod.POST.name(), apiPath);

        // when & then
        mockMvc.perform(post(apiPath)
                        .content(request.getBytes())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
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
                            "socialMediaProfileLinks": []
                        }
                        """,
                        """
                        {
                            "imagePath": "test-imagePath",
                            "description": "test-description",
                            "profileImageUri": "http://test.com",
                            "socialMediaProfileLinks": []
                        }
                        """,
                        """
                        {
                            "imagePath": "test-imagePath",
                            "introduction": "test-introduction",
                            "profileImageUri": "http://test.com",
                            "socialMediaProfileLinks": []
                        }
                        """
        );
    }

    @DisplayName("인플루언서 ID로 인플루언서를 조회하면, 200 OK 상태코드와 인플루언서 정보를 반환한다.")
    @Test
    void get() throws Exception {

        // given
        String id = "test-id";
        Influencer influencer = Influencer.builder()
                .id(id)
                .name(InfluencerName.from("test-influencer-nickname"))
                .introduction(InfluencerIntroduction.from("test-introduction"))
                .description(InfluencerDescription.from("test-description"))
                .keywords(Keywords.EMPTY)
                .socialMediaProfileLinks(SocialMediaProfileLinks.from(
                        Set.of(
                                SocialMediaProfileLink.of(SocialMediaPlatform.INSTAGRAM, "http://instagram.com/test")
                        )
                ))
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .build();
        String profileImageUrl = "http://cdn.inflpick.com" + influencer.getProfileImagePath();


        // when & then
        Mockito.when(queryService.query(id))
                .thenReturn(influencer);
        Mockito.when(storageService.getUrlString(influencer.getProfileImagePath()))
                .thenReturn(profileImageUrl);

        mockMvc.perform(MockMvcRequestBuilders.get("/influencers/{uuid}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("test-influencer-nickname"))
                .andExpect(jsonPath("$.introduction").value("test-introduction"))
                .andExpect(jsonPath("$.description").value("test-description"))
                .andExpect(jsonPath("$.socialMediaProfileLinks[0].platform").value("INSTAGRAM"))
                .andExpect(jsonPath("$.socialMediaProfileLinks[0].url").value("http://instagram.com/test"))
                .andExpect(jsonPath("$.profileImageUrl").value(profileImageUrl))
                .andExpect(jsonPath("$.keywords").isEmpty())
                .andDo(print());

    }

    @DisplayName("존재하지 않는 UUID로 인플루언서를 조회하면, 404 상태 코드를 반환한다.")
    @Test
    void read_notFound() throws Exception {

        // given
        String apiPath = "/influencers/test-uuid";
        Instant now = Instant.now();
        InstantHolder instantHolder = new TestInstantHolder(now);
        InfluencerReadApiErrorCode apiErrorCode = InfluencerReadApiErrorCode.INFLUENCER_NOT_FOUND;
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.of(instantHolder, apiErrorCode, HttpMethod.GET.name(), apiPath);
        String uuid = "test-uuid";

        // when & then
        Mockito.when(queryService.query(uuid))
                .thenThrow(new InfluencerNotFoundException(uuid));

        mockMvc.perform(MockMvcRequestBuilders.get("/influencers/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.status").value(apiErrorResponse.status()))
                .andExpect(jsonPath("$.code").value(apiErrorResponse.code()))
                .andExpect(jsonPath("$.reason").value(apiErrorResponse.reason()))
                .andExpect(jsonPath("$.action").value(apiErrorResponse.action()))
                .andExpect(jsonPath("$.method").value(apiErrorResponse.method()))
                .andExpect(jsonPath("$.path").value(apiErrorResponse.path()))
                .andDo(print());

    }

}