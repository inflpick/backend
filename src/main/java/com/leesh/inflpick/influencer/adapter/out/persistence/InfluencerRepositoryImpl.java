package com.leesh.inflpick.influencer.adapter.out.persistence;

import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerMongoRepository;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class InfluencerRepositoryImpl implements InfluencerRepository {

    private final InfluencerMongoRepository influencerMongoRepository;

    @Override
    public Influencer save(@NotNull Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        return influencerMongoRepository.save(document)
                .toEntity();
    }

    @Override
    public @NotNull Influencer getById(@NotNull String id) throws InfluencerNotFoundException {
        return influencerMongoRepository.findById(id)
                .map(InfluencerDocument::toEntity)
                .orElseThrow(() -> new InfluencerNotFoundException(id));
    }

    @Override
    public Optional<Influencer> findById(@NotNull String id) {
        return influencerMongoRepository.findById(id)
                .map(InfluencerDocument::toEntity);
    }

    @Override
    public long count() {
        return influencerMongoRepository.count();
    }

    @Override
    public @NotNull Influencer getByUuid(String uuid) throws InfluencerNotFoundException {
        return influencerMongoRepository.findByUuid(uuid)
                .map(InfluencerDocument::toEntity)
                .orElseThrow(() -> new InfluencerNotFoundException(uuid));
    }
}
