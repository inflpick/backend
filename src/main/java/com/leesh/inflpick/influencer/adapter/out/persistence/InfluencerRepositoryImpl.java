package com.leesh.inflpick.influencer.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.PageSortConverter;
import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerMongoRepository;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.influencer.port.out.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
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
    public PageDetails<Collection<Influencer>> getPage(PageQuery query) {

        Sort sortCriteria = PageSortConverter.convertSortCriteria(query.sortPairs());
        PageRequest pageRequest = PageRequest.of(query.page(),
                query.size(),
                sortCriteria);

        Page<InfluencerDocument> documentPage = influencerMongoRepository.findAll(pageRequest);
        List<InfluencerDocument> content = documentPage.getContent();
        Set<String> allKeywordIds = getAllKeywordIds(content);
        List<Influencer> contents = convertToEntities(allKeywordIds, content);

        String[] sortProperties = PageSortConverter.convertSortProperties(documentPage.getSort());

        return PageDetails.of(
                documentPage.getNumber(),
                documentPage.getSize(),
                documentPage.getTotalPages(),
                documentPage.getTotalElements(),
                sortProperties,
                contents);
    }

    private @NotNull List<Influencer> convertToEntities(Set<String> allKeywordIds, List<InfluencerDocument> content) {
        Keywords allKeywords = keywordRepository.getAllByIds(allKeywordIds);
        return content.stream().map(influencerDocument -> {
            Set<String> keywordIds = influencerDocument.getKeywordIds();
            Keywords keywords = allKeywords.subset(keywordIds);
            return influencerDocument.toEntity(keywords);
        }).toList();
    }

    private static @NotNull Set<String> getAllKeywordIds(List<InfluencerDocument> content) {
        Set<String> allKeywordIds = new HashSet<>();
        content.forEach(influencerDocument -> allKeywordIds.addAll(influencerDocument.getKeywordIds()));
        return allKeywordIds;
    }
}
