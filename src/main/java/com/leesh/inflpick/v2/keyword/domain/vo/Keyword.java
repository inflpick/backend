package com.leesh.inflpick.v2.keyword.domain.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Builder(access = AccessLevel.PUBLIC, builderMethodName = "requiredBuilder")
@Getter
public class Keyword {

    @Builder.Default
    private final KeywordId id = KeywordId.empty();
    private KeywordName name;
    @Builder.Default
    private KeywordColor color = KeywordColor.withDefault();
    @Builder.Default
    private final Instant createdDate = Instant.MIN;
    @Builder.Default
    private final Instant lastModifiedDate = Instant.MIN;

    public static KeywordBuilder builder(KeywordName name) {
        return requiredBuilder()
                .name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return Objects.equals(id, keyword.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void update(KeywordName name, KeywordColor color) {
        this.name = name;
        this.color = color;
    }

    /* Business Logic */
}
