package com.leesh.inflpick.influencer.port.out;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.InfluencerSortType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface InfluencerRepository {

    String save(@NotNull Influencer influencer);

    long count();

    @NotNull Influencer getById(String uuid) throws InfluencerNotFoundException;

    void deleteById(String id);

    PageDetails<Collection<Influencer>> getPage(PageQuery<InfluencerSortType> query);
}
