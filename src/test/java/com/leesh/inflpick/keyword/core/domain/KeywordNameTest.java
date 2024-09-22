package com.leesh.inflpick.keyword.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KeywordNameTest {

    @Test
    @DisplayName("유효한 키워드 명으로 KeywordName 생성")
    void from_validKeywordName_createsKeywordName() {
        // given
        String validName = "유효한키워드";

        // when
        KeywordName keywordName = new KeywordName(validName);

        // then
        assertEquals(validName, keywordName.name());
    }

    @Test
    @DisplayName("최소 길이의 키워드 명으로 KeywordName 생성")
    void from_minLengthKeywordName_createsKeywordName() {
        // given
        String minLengthName = "가";

        // when
        KeywordName keywordName = new KeywordName(minLengthName);

        // then
        assertEquals(minLengthName, keywordName.name());
    }

    @Test
    @DisplayName("최대 길이의 키워드 명으로 KeywordName 생성")
    void from_maxLengthKeywordName_createsKeywordName() {
        // given
        String maxLengthName = "가".repeat(30);

        // when
        KeywordName keywordName = new KeywordName(maxLengthName);

        // then
        assertEquals(maxLengthName, keywordName.name());
    }

    @Test
    @DisplayName("유효하지 않은 키워드 명으로 예외 발생")
    void from_invalidKeywordName_throwsException() {
        // given
        String invalidName = "유효하지않은키워드!";

        // when & then
        KeywordNameValidationFailedException exception = assertThrows(KeywordNameValidationFailedException.class, () -> {
            new KeywordName(invalidName);
        });
    }

    @Test
    @DisplayName("빈 키워드 명으로 예외 발생")
    void from_emptyKeywordName_throwsException() {
        // given
        String emptyName = "";

        // when & then
        KeywordNameValidationFailedException exception = assertThrows(KeywordNameValidationFailedException.class, () -> {
            new KeywordName(emptyName);
        });
    }

    @Test
    @DisplayName("null 키워드 명으로 예외 발생")
    void from_nullKeywordName_throwsException() {
        // given
        String nullName = null;

        // when & then
        assertThrows(NullPointerException.class, () -> {
            new KeywordName(nullName);
        });
    }
}