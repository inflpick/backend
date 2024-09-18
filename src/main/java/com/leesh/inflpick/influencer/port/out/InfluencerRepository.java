package com.leesh.inflpick.influencer.port.out;

import com.leesh.inflpick.influencer.adapter.out.persistence.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface InfluencerRepository {

    Influencer save(@NotNull Influencer influencer);

    @NotNull Influencer getById(@NotNull String id) throws InfluencerNotFoundException;

    @Nullable Optional<Influencer> findById(@NotNull String id);

    long count();

    @NotNull Influencer getByUuid(String uuid) throws InfluencerNotFoundException;
}
