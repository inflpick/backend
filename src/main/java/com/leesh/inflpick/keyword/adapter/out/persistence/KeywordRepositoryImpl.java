package com.leesh.inflpick.keyword.adapter.out.persistence;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.adapter.out.persistence.mongo.KeywordDocument;
import com.leesh.inflpick.keyword.adapter.out.persistence.mongo.KeywordMongoRepository;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class KeywordRepositoryImpl implements KeywordRepository {

    private final KeywordMongoRepository keywordMongoRepository;

    @Override
    public void save(Keyword keyword) {
        KeywordDocument document = KeywordDocument.from(keyword);
        keywordMongoRepository.save(document);
    }

    @Override
    public Keyword getByUuid(String uuid) throws KeywordNotFoundException {
        return keywordMongoRepository.findByUuid(uuid)
                .map(KeywordDocument::toEntity)
                .orElseThrow(() -> new KeywordNotFoundException(uuid));
    }

    @Override
    public long count() {
        return keywordMongoRepository.count();
    }

    @Override
    public Optional<Keyword> findByName(KeywordName name) {
        return keywordMongoRepository.findByName(name.name())
                .map(KeywordDocument::toEntity);
    }

    @Override
    public List<Keyword> search(KeywordName name) {
        return keywordMongoRepository.searchBy(name.name())
                .stream()
                .map(KeywordDocument::toEntity)
                .toList();
    }

    @Override
    public Keywords getAllByUuids(Set<String> keywordUuids) {
        List<KeywordDocument> keywordDocuments = keywordMongoRepository.findAllByUuidIn(keywordUuids);
        Set<Keyword> keywords = keywordDocuments.stream()
                .map(KeywordDocument::toEntity)
                .collect(Collectors.toSet());
        return new Keywords(keywords);
    }
}
