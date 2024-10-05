package com.leesh.inflpick.mock;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.InfluencerSortType;
import com.leesh.inflpick.influencer.port.out.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FakeInfluencerRepository implements InfluencerRepository {

    private final List<Influencer> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public String save(@NotNull Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        if (data.stream().anyMatch(d -> d.getId().equals(document.getId()))) {
            data.removeIf(d -> d.getId().equals(document.getId()));
            data.add(influencer);
        } else {
            data.add(influencer);
        }
        return influencer.getId();
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public @NotNull Influencer getById(String uuid) throws InfluencerNotFoundException {
        return data.stream()
                .filter(d -> d.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new InfluencerNotFoundException(uuid));
    }

    @Override
    public void deleteById(String id) {
        data.removeIf(d -> d.getId().equals(id));
    }

    @Override
    public PageDetails<Collection<Influencer>> getPage(PageQuery<InfluencerSortType> query) {
        return null;
    }

}
