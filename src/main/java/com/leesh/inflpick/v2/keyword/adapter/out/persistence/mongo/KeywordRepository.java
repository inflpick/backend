package com.leesh.inflpick.v2.keyword.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.keyword.application.port.out.CommandKeywordPort;
import com.leesh.inflpick.v2.keyword.application.port.out.SearchKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class KeywordRepository implements SearchKeywordPort, CommandKeywordPort {

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
}
