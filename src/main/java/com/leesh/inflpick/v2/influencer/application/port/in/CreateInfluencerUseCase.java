package com.leesh.inflpick.v2.influencer.application.port.in;

import com.leesh.inflpick.v2.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;

public interface CreateInfluencerUseCase {

    InfluencerId create(InfluencerRequest request);

}
