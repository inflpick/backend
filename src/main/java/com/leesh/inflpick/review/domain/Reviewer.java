package com.leesh.inflpick.review.domain;

import com.leesh.inflpick.influencer.domain.vo.InfluencerId;

public record Reviewer(InfluencerId id) {

    /* Business Logic */
    public static Reviewer create(InfluencerId id) {
        return new Reviewer(id);
    }

    public static Reviewer empty() {
        return new Reviewer(InfluencerId.empty());
    }
}
