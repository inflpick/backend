package com.leesh.inflpick.v2.influencer.application.port.in;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;

public interface QueryInfluencerUseCase {

    Influencer query(InfluencerId influencerId);
}
