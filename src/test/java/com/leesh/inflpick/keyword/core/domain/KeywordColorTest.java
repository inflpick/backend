package com.leesh.inflpick.keyword.core.domain;

import com.leesh.inflpick.influencer.core.domain.CustomConstraintViolationExceptionBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeywordColorTest {

    @Test
    @DisplayName("유효한 6자리 16진수 색상 코드로 KeywordColor 생성")
    void from_validHexColor_createsKeywordColor() {
        // given
        String hexColor = "#FFFFFF";

        // when
        KeywordColor keywordColor = KeywordColor.from(hexColor);

        // then
        assertEquals(hexColor, keywordColor.hexColor());
    }

    @Test
    @DisplayName("유효하지 않은 16진수 색상 코드로 예외 발생")
    void from_invalidHexColor_throwsException() {
        // given
        String hexColor = "#ZZZZZZ";

        // when & then
        HexColorSyntaxException exception = assertThrows(HexColorSyntaxException.class, () -> {
            KeywordColor.from(hexColor);
        });
        assertTrue(exception.getMessage().contains("키워드 색상은 16진수 형식으로 입력해야 합니다."));
    }

    @Test
    @DisplayName("빈 16진수 색상 코드로 예외 발생")
    void from_emptyHexColor_throwsException() {
        // given
        String hexColor = "";

        // when & then
        HexColorSyntaxException exception = assertThrows(HexColorSyntaxException.class, () -> {
            KeywordColor.from(hexColor);
        });
        assertTrue(exception.getMessage().contains("키워드 색상은 16진수 형식으로 입력해야 합니다."));
    }

    @Test
    @DisplayName("null 16진수 색상 코드로 예외 발생")
    void from_nullHexColor_throwsException() {
        // given
        String hexColor = null;

        // when & then
        assertThrows(NullPointerException.class, () -> KeywordColor.from(hexColor));
    }
}