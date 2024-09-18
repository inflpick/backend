package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.MissingRequiredFieldsException;
import com.leesh.inflpick.keyword.core.domain.KeywordNameValidationFailedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class KeywordControllerTest {

    private KeywordController sut;

    @BeforeEach
    void init() {
        sut = KeywordController.builder()
                .createService(command -> command.toEntity(() -> "test-uuid"))
                .build();
    }

    @DisplayName("키워드를 생성할 수 있다.")
    @Test
    void create() {
        // given
        KeywordRequest request = KeywordRequest.builder()
                .name("test introduction")
                .hexColor("#FFFFFF")
                .build();

        // when
        ResponseEntity<Void> result = sut.create(request);

        // then
        Assertions.assertThat(result.getStatusCode().value()).isEqualTo(201);
        Assertions.assertThat(result.getBody()).isNull();
    }

    @DisplayName("키워드 생성 시 이름이 비어있으면 실패한다.")
    @Test
    void createFailsWhenNameIsEmpty() {

        // when
        Throwable thrown = Assertions.catchThrowable(() -> KeywordRequest.builder()
                .name("")
                .hexColor("#FFFFFF")
                .build());

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(MissingRequiredFieldsException.class);
    }

    @DisplayName("키워드 생성 시 이름이 30자를 초과하면 실패한다.")
    @Test
    void createFailsWhenNameExceedsMaxLength() {
        // given
        String longName = "a".repeat(31);
        KeywordRequest request = KeywordRequest.builder()
                .name(longName)
                .hexColor("#FFFFFF")
                .build();

        // when
        Throwable thrown = Assertions.catchThrowable(() -> sut.create(request));

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(KeywordNameValidationFailedException.class);
    }

    @DisplayName("키워드 생성 시 이름에 특수문자가 포함되면 실패한다.")
    @Test
    void createFailsWhenNameContainsSpecialCharacters() {
        // given
        KeywordRequest request = KeywordRequest.builder()
                .name("invalid@introduction")
                .hexColor("#FFFFFF")
                .build();

        // when
        Throwable thrown = Assertions.catchThrowable(() -> sut.create(request));

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(KeywordNameValidationFailedException.class);
    }

}