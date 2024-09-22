package com.leesh.inflpick.influencer.core.domain.value;

public record InfluencerId(String id) {
    public InfluencerId {
        if (id == null) {
            throw new IllegalArgumentException("uuid must not be null");
        }
        if (id.isBlank()) {
            throw new IllegalArgumentException("uuid must not be blank");
        }
    }
}
