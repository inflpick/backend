package com.leesh.inflpick.influencer.application.port.out;

import com.leesh.inflpick.influencer.domain.Influencer;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;

public interface CommandInfluencerPort {

    InfluencerId save(Influencer influencer);

    void delete(InfluencerId id);
}
