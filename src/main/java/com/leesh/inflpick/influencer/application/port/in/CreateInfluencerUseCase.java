package com.leesh.inflpick.influencer.application.port.in;

import com.leesh.inflpick.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;

public interface CreateInfluencerUseCase {

    InfluencerId create(InfluencerRequest request);

}
