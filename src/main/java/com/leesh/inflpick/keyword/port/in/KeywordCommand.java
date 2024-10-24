package com.leesh.inflpick.keyword.port.in;

import com.leesh.inflpick.v2.shared.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordColor;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

@Builder
public record KeywordCommand(@NotNull KeywordName name,
                             @NotNull KeywordColor color) {

    public Keyword toEntity(UuidHolder uuidHolder) {
        return Keyword.builder()
                .id(uuidHolder.uuid())
                .name(name)
                .color(color)
                .build();
    }

}
