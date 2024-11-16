package com.leesh.inflpick.influencer.application.port.out;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.influencer.domain.Influencer;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;

import java.util.Optional;

public interface QueryInfluencerPort {

    Optional<Influencer> query(InfluencerId id);

    PageResponse<Influencer> query(PageRequest request);
}
