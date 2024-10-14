package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.influencer.core.domain.Influencer;

import java.util.Collection;

public interface InfluencerQueryService {

    Influencer getById(String id);

    PageDetails<Collection<Influencer>> getPage(PageQuery query);

}
