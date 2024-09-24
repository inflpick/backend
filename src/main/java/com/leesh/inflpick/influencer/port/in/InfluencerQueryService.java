package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.common.core.PageDetails;
import com.leesh.inflpick.common.core.PageQuery;
import com.leesh.inflpick.influencer.core.domain.Influencer;

import java.util.List;

public interface InfluencerQueryService {

    Influencer getById(String id);

    PageDetails<List<Influencer>> getPage(PageQuery query);

}
