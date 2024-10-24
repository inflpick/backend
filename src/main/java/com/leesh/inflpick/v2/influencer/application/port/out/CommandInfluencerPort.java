package com.leesh.inflpick.v2.influencer.application.port.out;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;

public interface CommandInfluencerPort {

    InfluencerId save(Influencer influencer);

    void delete(InfluencerId id);
}
