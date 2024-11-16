package com.leesh.inflpick.influencer.application.port.in;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;

public interface GetInfluencerUseCase {

    InfluencerResponse get(InfluencerId id);

    PageResponse<InfluencerResponse> getPage(PageRequest request);
}
