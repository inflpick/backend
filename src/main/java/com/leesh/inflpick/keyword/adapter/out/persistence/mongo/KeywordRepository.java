package com.leesh.inflpick.keyword.adapter.out.persistence.mongo;

import com.leesh.inflpick.common.adapter.out.persistence.SpringDataPageRequestConverter;
import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.common.application.dto.Sortable;
import com.leesh.inflpick.keyword.application.port.out.CommandKeywordPort;
import com.leesh.inflpick.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.keyword.application.port.out.SearchKeywordPort;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        String id = keywordMongoRepository.save(document).id();
        return KeywordId.create(id);
    }

    @Override
    public void delete(KeywordName name) {
            keywordMongoRepository.deleteByName(name.name());
    }

    @Override
    public void delete(KeywordId id) {
        keywordMongoRepository.deleteById(id.id());
    }

    @Override
    public List<Keyword> search(KeywordName name) {
        return keywordMongoRepository.searchBy(name.name())
                .stream().map(KeywordDocument::toEntity)
                .toList();
    }

    @Override
    public Optional<Keyword> query(KeywordId id) {
        return keywordMongoRepository.findById(id.id())
                .map(KeywordDocument::toEntity);
    }

    @Override
    public Optional<Keyword> query(KeywordName name) {
        return keywordMongoRepository.findByName(name.name())
                .map(KeywordDocument::toEntity);
    }

    @Override
    public List<Keyword> query(List<KeywordId> ids) {
        List<String> keywordIds = ids.stream().map(KeywordId::id)
                .toList();
        return keywordMongoRepository.findAllById(keywordIds)
                .stream().map(KeywordDocument::toEntity)
                .toList();
    }

    @Override
    public PageResponse<Keyword> query(PageRequest request) {
        Sortable sortable = () -> Arrays.stream(KeywordSortable.values())
                .map(KeywordSortable::name)
                .toList();
        org.springframework.data.domain.PageRequest pageRequest = SpringDataPageRequestConverter.convert(request, sortable);
        Page<KeywordDocument> keywordPage = keywordMongoRepository.findAll(pageRequest);
        List<Keyword> keywords = keywordPage
                .map(KeywordDocument::toEntity)
                .stream()
                .toList();
        return PageResponse.create(keywords,
                keywordPage.getNumber(),
                keywordPage.getTotalPages(),
                keywordPage.getSize(),
                keywordPage.getTotalElements(),
                keywordPage.getSort().toString());
    }
}
