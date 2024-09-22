package com.leesh.inflpick.product.adapter.out.persistence;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductDocument;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.InfluencerMongoRepository;
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
        ProductDocument document = ProductDocument.from(influencer);
        influencerMongoRepository.save(document);
        return this.getByUuid(document.getUuid());
    }

    @Override
    public long count() {
        return influencerMongoRepository.count();
    }

    @Override
    public @NotNull Influencer getByUuid(@NotNull String uuid) throws InfluencerNotFoundException {
        ProductDocument productDocument = influencerMongoRepository.findByUuid(uuid)
                .orElseThrow(() -> new InfluencerNotFoundException(uuid));

        Set<String> keywordUuids = productDocument.getKeywordUuids();
        Keywords keywords = keywordRepository.getAllByUuids(keywordUuids);

        return productDocument.toEntity(keywords);
    }
}
