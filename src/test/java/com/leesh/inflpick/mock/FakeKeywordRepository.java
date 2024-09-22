package com.leesh.inflpick.mock;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.port.out.KeywordNotFoundException;
import com.leesh.inflpick.keyword.adapter.out.persistence.mongo.KeywordDocument;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;

import java.util.*;
import java.util.stream.Collectors;

public class FakeKeywordRepository implements KeywordRepository {

    private final List<Keyword> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(Keyword keyword) {
        KeywordDocument document = KeywordDocument.from(keyword);
        if (data.stream().anyMatch(d -> d.getId().equals(document.getId()))) {
            data.removeIf(d -> d.getId().equals(document.getId()));
            data.add(keyword);
        } else {
            data.add(keyword);
        }
    }

    @Override
    public Keyword getById(String id) throws KeywordNotFoundException {
        return data.stream()
                .filter(keyword -> keyword.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new KeywordNotFoundException(id));
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public Optional<Keyword> findByName(KeywordName name) {
        return data.stream()
                .filter(keyword -> keyword.getName().equals(name.name()))
                .findFirst();
    }

    @Override
    public List<Keyword> search(KeywordName name) {
        return data;
    }

    @Override
    public Keywords getAllByIds(Set<String> keywordIds) {
        Set<Keyword> keywords = data.stream()
                .filter(keyword -> keywordIds.contains(keyword.getId()))
                .collect(Collectors.toSet());

        return new Keywords(keywords);
    }
}
