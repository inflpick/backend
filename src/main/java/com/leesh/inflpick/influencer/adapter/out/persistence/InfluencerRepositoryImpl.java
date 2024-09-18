package com.leesh.inflpick.influencer.adapter.out.persistence;

import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerMongoRepository;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.Keywords;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Set;

@RequiredArgsConstructor
@Repository
public class InfluencerRepositoryImpl implements InfluencerRepository {

    private final InfluencerMongoRepository influencerMongoRepository;
    private final KeywordRepository keywordRepository;

    @Override
    public Influencer save(@NotNull Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        influencerMongoRepository.save(document);
        return this.getByUuid(document.getUuid());
    }

    @Override
    public long count() {
        return influencerMongoRepository.count();
    }

    @Override
    public @NotNull Influencer getByUuid(@NotNull String uuid) throws InfluencerNotFoundException {
        InfluencerDocument influencerDocument = influencerMongoRepository.findByUuid(uuid)
                .orElseThrow(() -> new InfluencerNotFoundException(uuid));

        Set<String> keywordUuids = influencerDocument.getKeywordUuids();
        Keywords keywords = keywordRepository.getAllByUuids(keywordUuids);

        return influencerDocument.toEntity(keywords);
    }
}
