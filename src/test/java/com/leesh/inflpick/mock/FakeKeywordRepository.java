package com.leesh.inflpick.mock;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.adapter.out.persistence.KeywordNotFoundException;
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
        if (data.stream().anyMatch(d -> d.getUuid().equals(document.getUuid()))) {
            data.removeIf(d -> d.getUuid().equals(document.getUuid()));
            data.add(keyword);
        } else {
            data.add(keyword);
        }
    }

    @Override
    public Keyword getByUuid(String uuid) throws KeywordNotFoundException {
        return data.stream()
                .filter(keyword -> keyword.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new KeywordNotFoundException(uuid));
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
    public Keywords getAllByUuids(Set<String> keywordUuids) {
        Set<Keyword> keywords = data.stream()
                .filter(keyword -> keywordUuids.contains(keyword.getUuid()))
                .collect(Collectors.toSet());

        return new Keywords(keywords);
    }
}
