package com.leesh.inflpick.mock;

import com.leesh.inflpick.influencer.adapter.out.persistence.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeInfluencerRepository implements InfluencerRepository {

    private final List<Influencer> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Influencer save(@NotNull Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        if (data.stream().anyMatch(d -> d.getUuid().equals(document.getUuid()))) {
            data.removeIf(d -> d.getUuid().equals(document.getUuid()));
            data.add(influencer);
        } else {
            data.add(influencer);
        }
        return influencer;
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public @NotNull Influencer getByUuid(String uuid) throws InfluencerNotFoundException {
        return data.stream()
                .filter(d -> d.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new InfluencerNotFoundException(uuid));
    }

}
