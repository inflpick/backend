package com.leesh.inflpick.v2.influencer.application.port.in;

import com.leesh.inflpick.v2.common.application.dto.PageRequest;
import com.leesh.inflpick.v2.common.application.dto.PageResponse;
import com.leesh.inflpick.v2.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;

public interface GetInfluencerUseCase {

    InfluencerResponse get(InfluencerId id);

    PageResponse<InfluencerResponse> getPage(PageRequest request);
}
