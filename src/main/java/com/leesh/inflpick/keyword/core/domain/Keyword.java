package com.leesh.inflpick.keyword.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

public class Keyword {

    @Getter
    private final String id;
    private final KeywordName name;
    private final KeywordColor color;

    @Builder
    public Keyword(String id, KeywordName name, KeywordColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
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
