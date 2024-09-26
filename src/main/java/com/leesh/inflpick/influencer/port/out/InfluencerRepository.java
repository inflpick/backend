package com.leesh.inflpick.influencer.port.out;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.InfluencerPageQuery;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface InfluencerRepository {

    String save(@NotNull Influencer influencer);

    long count();

    @NotNull Influencer getById(String uuid) throws InfluencerNotFoundException;

    void deleteById(String id);

    PageDetails<List<Influencer>> getPage(InfluencerPageQuery query);

    void deleteAll();
}
