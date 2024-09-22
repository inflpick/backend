package com.leesh.inflpick.keyword.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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