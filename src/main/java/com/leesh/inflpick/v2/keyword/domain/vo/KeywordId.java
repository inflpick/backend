package com.leesh.inflpick.v2.keyword.domain.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public class KeywordId {

    private final String value;

    private KeywordId() {
        this.value = "";
    }

    private KeywordId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeywordId keywordId = (KeywordId) o;
        return Objects.equals(value, keywordId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /* Business Logic */
    public static KeywordId create(String value) {
        return new KeywordId(value);
    }

    public static KeywordId empty() {
        return new KeywordId();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }
}
