package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.common.adapter.out.time.InstantHolder;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordCreateApiErrorCode;
import com.leesh.inflpick.keyword.port.in.KeywordCreateCommand;
import com.leesh.inflpick.keyword.port.in.KeywordCreateService;
import com.leesh.inflpick.keyword.port.in.KeywordReadService;
import com.leesh.inflpick.mock.TestInstantHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KeywordController.class)
class KeywordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private KeywordCreateService createService;
    @MockBean
    private KeywordReadService readService;

    @DisplayName("키워드 생성 API 요청 시, 정상 입력 값을 입력하면, 201 Created 상태코드와 생성된 리소스 URI를 반환한다.")
    @MethodSource("provideValidJsonRequests")
    @ParameterizedTest
    void create(String jsonRequest) throws Exception {

        // given
        String apiPath = "/keywords";
        String uriString = UriComponentsBuilder.fromHttpUrl("http://localhost").path(apiPath).toUriString();

        // when & then
        String testUuid = "test-id";
        Mockito.when(createService.create(
                Mockito.any(KeywordCreateCommand.class)))
                        .thenReturn(testUuid);

        mockMvc.perform(post(apiPath)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string(HttpHeaders.LOCATION, uriString + "/" + testUuid))
                .andDo(print())
        ;
    }

    private static Stream<String> provideValidJsonRequests() {
        return Stream.of(
                """
                    {
                        "name": "100만 유튜버",
                        "hexColor": "#FFFFFF"
                    }
                    """
        );
    }

    @DisplayName("키워드 생성 API 요청 시, 이름이 잘못된 값이면, 400 Bad Request 상태코드를 반환한다.")
    @MethodSource("provideInvalidJsonRequests")
    @ParameterizedTest
    void createFailsWhenNameIsEmpty(String jsonRequest) throws Exception {

        // given
        String apiPath = "/keywords";
        Instant now = Instant.now();
        InstantHolder instantHolder = new TestInstantHolder(now);
        KeywordCreateApiErrorCode apiErrorCode = KeywordCreateApiErrorCode.KEYWORD_NAME_VALIDATE_FAILED;

        // when & then
        mockMvc.perform(post(apiPath)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
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
                    "name": "test-name",
                    "description": "test-description",
                    "profileImageUri": "http://test.com",
                    "socialMediaProfileLinks": []
                }
                """,
                """
                {
                    "name": "test-name",
                    "introduction": "test-introduction",
                    "profileImageUri": "http://test.com",
                    "socialMediaProfileLinks": []
                }
                """,
                """
                {
                    "name": "test-name",
                    "introduction": "test-introduction",
                    "description": "test-description",
                    "socialMediaProfileLinks": []
                }
                """
        );
    }

}