package com.leesh.inflpick.keyword.adapter.out.persistence;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.adapter.out.persistence.mongo.KeywordDocument;
import com.leesh.inflpick.keyword.adapter.out.persistence.mongo.KeywordMongoRepository;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import com.leesh.inflpick.keyword.port.out.KeywordNotFoundException;
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
    public Keyword getById(String id) throws KeywordNotFoundException {
        return keywordMongoRepository.findById(id)
                .map(KeywordDocument::toEntity)
                .orElseThrow(() -> new KeywordNotFoundException(id));
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
    public List<Keyword> search(String name) {
        return keywordMongoRepository.searchBy(name)
                .stream()
                .map(KeywordDocument::toEntity)
                .toList();
    }

    @Override
    public Keywords getAllByIds(Set<String> keywordIds) {
        List<KeywordDocument> keywordDocuments = keywordMongoRepository.findAllByIdIn(keywordIds);
        Set<Keyword> keywords = keywordDocuments.stream()
                .map(KeywordDocument::toEntity)
                .collect(Collectors.toSet());
        return new Keywords(keywords);
    }

    @Override
    public Optional<Keyword> findById(String id) {
        return keywordMongoRepository.findById(id)
                .map(KeywordDocument::toEntity);
    }
}
