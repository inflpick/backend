package com.leesh.inflpick.influencer.port.out;

import com.leesh.inflpick.common.core.PageDetails;
import com.leesh.inflpick.common.core.PageQuery;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface InfluencerRepository {

    String save(@NotNull Influencer influencer);

    long count();

    @NotNull Influencer getById(String uuid) throws InfluencerNotFoundException;

    void deleteById(String id);

    PageDetails<List<Influencer>> getPage(PageQuery query);
}
