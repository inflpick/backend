package com.leesh.inflpick.keyword.port.in;

import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordColor;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordCreateCommandTest {

    @Test
    @DisplayName("KeywordCreateCommand 객체로 키워드를 생성해야 한다")
    void createKeywordFromCommand() {
        // given
        KeywordName name = new KeywordName("example");
        KeywordColor color = KeywordColor.from("#FFFFFF");
        KeywordCreateCommand command = KeywordCreateCommand.builder()
                .name(name)
                .color(color)
                .build();

        // when
        Keyword keyword = command.toEntity(
                () -> "test-uuid"
        );

        // then
        assertThat(keyword.getName()).isEqualTo(name.name());
        assertThat(keyword.getHexColor()).isEqualTo(color.hexColor());
    }
}