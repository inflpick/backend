package com.leesh.inflpick.v2.keyword.application.port.out;

import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

import java.util.Optional;
import java.util.Set;

public interface QueryKeywordPort {

    Optional<Keyword> query(KeywordId id);

    Optional<Keyword> query(KeywordName name);

    Set<Keyword> query(Set<KeywordId> ids);
}
