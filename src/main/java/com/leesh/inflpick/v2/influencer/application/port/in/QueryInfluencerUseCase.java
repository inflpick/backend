package com.leesh.inflpick.v2.influencer.application.port.in;

import com.leesh.inflpick.v2.influencer.application.dto.QueryInfluencerResponse;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.shared.application.dto.PageRequest;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;

public interface QueryInfluencerUseCase {

    QueryInfluencerResponse query(InfluencerId influencerId);


    PageResponse<QueryInfluencerResponse> query(PageRequest request);
}
