package com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class InfluencerRepository implements CommandInfluencerPort, QueryInfluencerPort {

    private InfluencerMongoRepository influencerMongoRepository;

    @Override
    public InfluencerId save(Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        InfluencerDocument saved = influencerMongoRepository.save(document);
        return InfluencerId.create(saved.getId());
    }

    @Override
    public void delete(InfluencerId id) {
        influencerMongoRepository.deleteById(id.getValue());
    }

    @Override
    public Optional<Influencer> query(InfluencerId id) {
        return influencerMongoRepository.findById(id.getValue())
                .map(InfluencerDocument::toEntity);
    }
}
