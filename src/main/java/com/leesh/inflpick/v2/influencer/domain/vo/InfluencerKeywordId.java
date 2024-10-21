package com.leesh.inflpick.v2.influencer.domain.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class InfluencerKeywordId {

    private final String value;

    private InfluencerKeywordId() {
        this.value = "";
    }

    private InfluencerKeywordId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfluencerKeywordId that = (InfluencerKeywordId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /* Business Logic */
    public static InfluencerKeywordId create(String value) {
        return new InfluencerKeywordId(value);
    }

    public static InfluencerKeywordId empty() {
        return new InfluencerKeywordId();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }
}
