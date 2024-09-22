package com.leesh.inflpick.influencer.port.out;

import com.leesh.inflpick.influencer.adapter.out.persistence.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import org.jetbrains.annotations.NotNull;

public interface InfluencerRepository {

    Influencer save(@NotNull Influencer influencer);

    long count();

    @NotNull Influencer getByUuid(String uuid) throws InfluencerNotFoundException;
}
