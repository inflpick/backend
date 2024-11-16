package com.leesh.inflpick.keyword.domain.vo;

public record KeywordId(String id) {

    /* Business Logic */
    public static KeywordId create(String id) {
        return new KeywordId(id);
    }

    public static KeywordId empty() {
        return new KeywordId(null);
    }

    public boolean isEmpty() {
        return id == null || id.isEmpty();
    }
}
