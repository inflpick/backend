package com.leesh.inflpick.keyword.core.service;

import com.leesh.inflpick.keyword.core.domain.KeywordColor;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import com.leesh.inflpick.keyword.port.in.KeywordCreateCommand;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.mock.FakeKeywordRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KeywordServiceImplTest {

    private KeywordServiceImpl sut;
    private KeywordRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FakeKeywordRepository();
        sut = KeywordServiceImpl.builder()
                .uuidHolder(() -> "test-id")
                .keywordRepository(repository)
                .build();
    }

    @Test
    @DisplayName("KeywordCreateCommand 객체로 키워드를 생성해야 한다")
    void createKeyword() {
        // given
        long count = repository.count();
        KeywordCreateCommand command = KeywordCreateCommand.builder()
                .name(new KeywordName("키워드"))
                .color(KeywordColor.from("#FFFFFF"))
                .build();

        // when
        sut.create(command);

        // then
        Assertions.assertThat(repository.count()).isEqualTo(count + 1);
    }
}