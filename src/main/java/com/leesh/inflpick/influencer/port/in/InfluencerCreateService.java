package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.Influencer;

public interface InfluencerCreateService {

    Influencer create(InfluencerCreateCommand command);

}
