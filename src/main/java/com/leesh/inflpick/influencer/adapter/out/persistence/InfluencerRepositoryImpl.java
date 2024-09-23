package com.leesh.inflpick.influencer.adapter.out.persistence;

import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerMongoRepository;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.influencer.port.out.InfluencerNotFoundException;
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
    public String save(@NotNull Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        influencerMongoRepository.save(document);
        return document.getId();
    }

    @Override
    public long count() {
        return influencerMongoRepository.count();
    }

    @Override
    public @NotNull Influencer getById(@NotNull String id) throws InfluencerNotFoundException {
        InfluencerDocument influencerDocument = influencerMongoRepository.findById(id)
                .orElseThrow(() -> new InfluencerNotFoundException(id));

        Set<String> keywordIds = influencerDocument.getKeywordIds();
        Keywords keywords = keywordRepository.getAllByIds(keywordIds);

        return influencerDocument.toEntity(keywords);
    }

    @Override
    public void deleteById(String id) {
        try {
            Influencer influencer = this.getById(id);
            influencer.delete();
            this.save(influencer);
        } catch (InfluencerNotFoundException e) {
            // do nothing
        }
    }
}
