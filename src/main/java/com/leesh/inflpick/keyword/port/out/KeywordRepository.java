package com.leesh.inflpick.keyword.port.out;

import com.leesh.inflpick.influencer.core.domain.Keywords;
import com.leesh.inflpick.keyword.adapter.out.persistence.KeywordNotFoundException;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordName;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface KeywordRepository {

    void save(Keyword keyword);

    Keyword getByUuid(String uuid) throws KeywordNotFoundException;

    long count();

    Optional<Keyword> findByName(KeywordName name);

    List<Keyword> search(KeywordName name);

    Keywords getAllByUuids(Set<String> keywordUuids);
}
