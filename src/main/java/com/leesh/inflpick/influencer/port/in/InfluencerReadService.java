package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.Influencer;

public interface InfluencerReadService {

    Influencer getByUuid(String uuid);

}
