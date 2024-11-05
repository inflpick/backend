package com.leesh.inflpick.v2.influencer.application.port.in;

import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;

public interface GetInfluencerUseCase {

    InfluencerResponse get(InfluencerId id);

    OffsetPageResponse<InfluencerResponse> getPage(OffsetPageRequest request);
}
