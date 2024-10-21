package com.leesh.inflpick.v2.domain.influencer.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class InfluencerId {

    private final String value;

    private InfluencerId() {
        this.value = "";
    }

    private InfluencerId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfluencerId that = (InfluencerId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /* Business Logic */
    public static InfluencerId create(String value) {
        return new InfluencerId(value);
    }

    public static InfluencerId empty() {
        return new InfluencerId();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }
}
