package com.leesh.inflpick.v2.review.domain;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;

public record Reviewer(InfluencerId id) {

    /* Business Logic */
    public static Reviewer create(InfluencerId id) {
        return new Reviewer(id);
    }

    public static Reviewer empty() {
        return new Reviewer(InfluencerId.empty());
    }
}
