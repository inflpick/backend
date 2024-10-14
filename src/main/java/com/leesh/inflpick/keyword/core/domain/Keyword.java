package com.leesh.inflpick.keyword.core.domain;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

public class Keyword {

    @Getter
    private final String id;
    private KeywordName name;
    private KeywordColor color;
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

    public void update(@NotNull KeywordName name, @NotNull KeywordColor color) {
        this.name = name;
        this.color = color;
    }
}
