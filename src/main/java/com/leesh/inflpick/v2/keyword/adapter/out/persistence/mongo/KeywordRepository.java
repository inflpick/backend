package com.leesh.inflpick.v2.keyword.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.keyword.application.port.out.CommandKeywordPort;
import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.keyword.application.port.out.SearchKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Repository
public class KeywordRepository implements SearchKeywordPort, CommandKeywordPort, QueryKeywordPort {

    private final KeywordMongoRepository keywordMongoRepository;

    @Override
    public KeywordId save(Keyword keyword) {
        KeywordDocument document = KeywordDocument.from(keyword);
        String id = keywordMongoRepository.save(document).getId();
        return KeywordId.create(id);
    }

    @Override
    public void delete(KeywordName name) {
        keywordMongoRepository.deleteByName(name.getValue());
    }

    @Override
    public List<Keyword> search(KeywordName name) {
        return keywordMongoRepository.searchBy(name.getValue())
                .stream().map(KeywordDocument::toEntity)
                .toList();
    }

    @Override
    public Optional<Keyword> query(KeywordId id) {
        return Optional.empty();
    }

    @Override
    public Optional<Keyword> query(KeywordName name) {
        return Optional.empty();
    }

    @Override
    public List<Keyword> query(InfluencerId influencerId) {
        return keywordMongoRepository.findByInfluencerId(influencerId.getValue())
                .stream().map(KeywordDocument::toEntity)
                .toList();
    }

    @Override
    public List<Keyword> query(List<KeywordId> ids) {
        List<String> keywordIds = ids.stream().map(KeywordId::getId)
                .toList();
        return keywordMongoRepository.findAllById(keywordIds)
                .stream().map(KeywordDocument::toEntity)
                .toList();
    }
}
