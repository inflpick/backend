package com.leesh.inflpick.v2.influencer.application.port.in;

import com.leesh.inflpick.v2.influencer.application.dto.InfluencerCommand;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;

public interface CommandInfluencerUseCase {

    InfluencerId create(InfluencerCommand influencerCommand);

}
