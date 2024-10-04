package com.leesh.inflpick.keyword.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class KeywordTest {

    @Test
    @DisplayName("유효한 키워드로 Keyword 생성")
    void from_validKeyword_createsKeyword() {
        // given
        String id = "1";
        KeywordName name = new KeywordName("유효한키워드");
        KeywordColor color = KeywordColor.from("#FFFFFF");

        // when
        Keyword keyword = Keyword.builder()
                .id(id)
                .name(name)
                .color(color)
                .build();

        // then
        assertEquals(id, keyword.getId());
        assertEquals(name.name(), keyword.getName());
        assertEquals(color.hexColor(), keyword.getHexColor());
    }

    @Test
    @DisplayName("동일한 ID로 생성된 두 Keyword는 동일")
    void keywords_withSameId_areEqual() {
        // given
        String id = "1";
        KeywordName name1 = new KeywordName("키워드1");
        KeywordColor color1 = KeywordColor.from("#FFFFFF");
        KeywordName name2 = new KeywordName("키워드2");
        KeywordColor color2 = KeywordColor.from("#000000");

        // when
        Keyword keyword1 = Keyword.builder()
                .id(id)
                .name(name1)
                .color(color1)
                .build();
        Keyword keyword2 = Keyword.builder()
                .id(id)
                .name(name2)
                .color(color2)
                .build();

        // then
        assertEquals(keyword1, keyword2);
    }

    @Test
    @DisplayName("다른 ID로 생성된 두 Keyword는 다름")
    void keywords_withDifferentId_areNotEqual() {
        // given
        KeywordName name = new KeywordName("키워드");
        KeywordColor color = KeywordColor.from("#FFFFFF");

        // when
        Keyword keyword1 = Keyword.builder()
                .id("1")
                .name(name)
                .color(color)
                .build();
        Keyword keyword2 = Keyword.builder()
                .id("2")
                .name(name)
                .color(color)
                .build();

        // then
        assertNotEquals(keyword1, keyword2);
    }

    @Test
    @DisplayName("Keyword의 이름과 색상 가져오기")
    void getNameAndColor_returnsCorrectValues() {
        // given
        KeywordName name = new KeywordName("키워드");
        KeywordColor color = KeywordColor.from("#FFFFFF");

        // when
        Keyword keyword = Keyword.builder()
                .id("1")
                .name(name)
                .color(color)
                .build();

        // then
        assertEquals("키워드", keyword.getName());
        assertEquals("#FFFFFF", keyword.getHexColor());
    }
}