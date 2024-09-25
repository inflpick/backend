package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.InfluencerPageQuery;

import java.util.List;

public interface InfluencerQueryService {

    Influencer getById(String id);

    PageDetails<List<Influencer>> getPage(InfluencerPageQuery query);

}
