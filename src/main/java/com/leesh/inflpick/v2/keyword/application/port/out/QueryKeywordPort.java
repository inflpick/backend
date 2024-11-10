package com.leesh.inflpick.v2.keyword.application.port.out;

import com.leesh.inflpick.v2.common.application.dto.PageRequest;
import com.leesh.inflpick.v2.common.application.dto.PageResponse;
import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

import java.util.List;
import java.util.Optional;

public interface QueryKeywordPort {

    Optional<Keyword> query(KeywordId id);

    Optional<Keyword> query(KeywordName name);

    List<Keyword> query(List<KeywordId> keywordIds);

    PageResponse<Keyword> query(PageRequest request);
}
