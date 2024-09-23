package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.out.InfluencerNotFoundException;

public interface InfluencerQueryService {

    Influencer getById(String id) throws InfluencerNotFoundException;

}
