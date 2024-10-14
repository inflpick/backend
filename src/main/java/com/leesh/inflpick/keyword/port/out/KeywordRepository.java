package com.leesh.inflpick.keyword.port.out;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordName;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface KeywordRepository {

    void save(Keyword keyword);

    Keyword getById(String id) throws KeywordNotFoundException;

    long count();

    Optional<Keyword> findByName(KeywordName name);

    List<Keyword> search(String name);

    Keywords getAllByIds(Set<String> keywordIds);

    Optional<Keyword> findById(String id);
}
