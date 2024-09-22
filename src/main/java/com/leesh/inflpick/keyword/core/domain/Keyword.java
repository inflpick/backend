package com.leesh.inflpick.keyword.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

public class Keyword {

    @Getter
    private final String uuid;
    private final KeywordName name;
    private final KeywordColor color;

    @Builder
    public Keyword(String uuid, KeywordName name, KeywordColor color) {
        this.uuid = uuid;
        this.name = name;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return Objects.equals(uuid, keyword.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    public String getName() {
        return name.name();
    }

    public String getHexColor() {
        return color.hexColor();
    }
}
