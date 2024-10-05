package com.leesh.inflpick.keyword.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

public class Keyword {

    @Getter
    private final String id;
    private final KeywordName name;
    private final KeywordColor color;
    @Getter
    private final Instant createdDate;
    @Getter
    private final Instant lastModifiedDate;

    @Builder
    public Keyword(String id, KeywordName name, KeywordColor color, Instant createdDate, Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
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

    public String getName() {
        return name.name();
    }

    public String getHexColor() {
        return color.hexColor();
    }
}
