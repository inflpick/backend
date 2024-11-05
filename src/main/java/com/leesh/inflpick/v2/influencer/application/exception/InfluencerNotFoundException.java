package com.leesh.inflpick.v2.influencer.application.exception;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;

public class InfluencerNotFoundException extends IllegalArgumentException {
    public InfluencerNotFoundException(InfluencerId influencerId) {
        super("Influencer not found. influencerId: " + influencerId);
    }
}
