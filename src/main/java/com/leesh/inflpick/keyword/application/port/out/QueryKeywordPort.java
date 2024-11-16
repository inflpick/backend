package com.leesh.inflpick.keyword.application.port.out;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;

import java.util.List;
import java.util.Optional;

public interface QueryKeywordPort {

    Optional<Keyword> query(KeywordId id);

    Optional<Keyword> query(KeywordName name);

    List<Keyword> query(List<KeywordId> keywordIds);

    PageResponse<Keyword> query(PageRequest request);
}
