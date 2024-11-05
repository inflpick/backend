package com.leesh.inflpick.v2.keyword.application.service;

import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.v2.keyword.application.exception.KeywordNotFoundException;
import com.leesh.inflpick.v2.keyword.application.port.in.GetKeywordUseCase;
import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetKeywordService implements GetKeywordUseCase {

    private final QueryKeywordPort queryKeywordPort;

    @Override
    public KeywordResponse get(KeywordId id) {
        Keyword keyword = queryKeywordPort.query(id)
                .orElseThrow(() -> new KeywordNotFoundException(id));
        return KeywordResponse.create(keyword);
    }

    @Override
    public OffsetPageResponse<KeywordResponse> getPage(OffsetPageRequest request) {
        OffsetPageResponse<Keyword> keywordPage = queryKeywordPort.query(request);
        List<KeywordResponse> keywordResponses = keywordPage.contents().stream()
                .map(KeywordResponse::create)
                .toList();
        return OffsetPageResponse.create(keywordResponses,
                keywordPage.currentPage(),
                keywordPage.totalPages(),
                keywordPage.size(),
                keywordPage.totalElements(),
                keywordPage.sortProperties());
    }
}
