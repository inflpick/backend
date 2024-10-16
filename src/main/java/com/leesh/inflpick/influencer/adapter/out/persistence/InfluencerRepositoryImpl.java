package com.leesh.inflpick.influencer.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.SpringDataPageRequestConverter;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerMongoRepository;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerPageResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.influencer.port.out.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.product.port.ProductSortProperty;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
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
        influencerMongoRepository.deleteById(id);
    }

    @Override
    public PageResponse<Influencer> getPage(com.leesh.inflpick.common.port.PageRequest request) {

        PageRequest pageRequest = SpringDataPageRequestConverter.convert(request, () -> Arrays.stream(ProductSortProperty.values())
                .map(ProductSortProperty::getValue)
                .toList());
        Page<InfluencerDocument> documentPage = influencerMongoRepository.findAll(pageRequest);
        List<InfluencerDocument> content = documentPage.getContent();
        List<Influencer> influencers = convertToEntities(content);
        String sortProperties = documentPage.getSort().toString();

        return InfluencerPageResponse.builder()
                .contents(influencers)
                .currentPage(documentPage.getNumber())
                .totalPages(documentPage.getTotalPages())
                .size(documentPage.getSize())
                .totalElements(documentPage.getTotalElements())
                .sortProperties(sortProperties)
                .build();
    }

    private @NotNull List<Influencer> convertToEntities(List<InfluencerDocument> content) {
        return content.stream().map(document -> {
            Set<String> keywordIds = document.getKeywordIds();
            Keywords influencerKeywords = keywordRepository.getAllByIds(keywordIds);
            return document.toEntity(influencerKeywords);
        }).toList();
    }
}
