package com.leesh.inflpick.influencer.application.port.in;

import com.leesh.inflpick.influencer.domain.vo.InfluencerId;

public interface DeleteInfluencerUseCase {

    void delete(InfluencerId id);
}
