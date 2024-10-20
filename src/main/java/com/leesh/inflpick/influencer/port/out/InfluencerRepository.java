package com.leesh.inflpick.influencer.port.out;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import org.jetbrains.annotations.NotNull;

public interface InfluencerRepository {

    String save(@NotNull Influencer influencer);

    long count();

    @NotNull Influencer getById(String uuid) throws InfluencerNotFoundException;

    void deleteById(String id);

    PageResponse<Influencer> getPage(PageRequest request);
}
