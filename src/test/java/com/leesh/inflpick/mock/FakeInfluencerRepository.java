package com.leesh.inflpick.mock;

import com.leesh.inflpick.influencer.adapter.out.persistence.ResourceNotFoundException;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongodb.InfluencerDocument;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.InfluencerId;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeInfluencerRepository implements InfluencerRepository {

    private final List<Influencer> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        if (data.stream().anyMatch(d -> d.getId().equals(document.getId()))) {
            data.removeIf(d -> d.getId().equals(document.getId()));
            data.add(influencer);
        } else {
            data.add(influencer);
        }
    }

    @Override
    public Influencer findById(InfluencerId influencerId) {
        return data.stream()
                .filter(influencer -> influencer.getId().equals(influencerId.id()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("인플루언서", influencerId.id()));
    }

    @Override
    public long count() {
        return data.size();
    }

}
