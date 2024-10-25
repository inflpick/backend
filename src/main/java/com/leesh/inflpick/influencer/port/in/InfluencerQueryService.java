package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.v2.shared.application.dto.PageRequest;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;

public interface InfluencerQueryService {

    Influencer query(String id);

    PageResponse<Influencer> query(PageRequest request);
}
