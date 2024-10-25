package com.leesh.inflpick.v2.influencer.application.port.out;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.shared.application.dto.PageRequest;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;

import java.util.Optional;

public interface QueryInfluencerPort {

    Optional<Influencer> query(InfluencerId id);

    PageResponse<Influencer> query(PageRequest request);
}
