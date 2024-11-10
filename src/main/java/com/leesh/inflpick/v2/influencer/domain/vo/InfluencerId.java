package com.leesh.inflpick.v2.influencer.domain.vo;

public record InfluencerId(String id) {

    /* Business Logic */
    public static InfluencerId create(String id) {
        return new InfluencerId(id);
    }

    public static InfluencerId empty() {
        return new InfluencerId(null);
    }

    public boolean isEmpty() {
        return id == null || id.isEmpty();
    }
}
