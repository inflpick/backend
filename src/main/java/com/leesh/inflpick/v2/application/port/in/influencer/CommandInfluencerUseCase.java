package com.leesh.inflpick.v2.application.port.in.influencer;

import com.leesh.inflpick.v2.application.dto.influencer.InfluencerCommand;
import com.leesh.inflpick.v2.domain.influencer.vo.InfluencerId;

public interface CommandInfluencerUseCase {

    InfluencerId create(InfluencerCommand influencerCommand);

}
