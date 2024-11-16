package com.leesh.inflpick.keyword.application.service;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.keyword.application.exception.KeywordNotFoundException;
import com.leesh.inflpick.keyword.application.port.in.GetKeywordUseCase;
import com.leesh.inflpick.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
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
    public PageResponse<KeywordResponse> getPage(PageRequest request) {
        PageResponse<Keyword> keywordPage = queryKeywordPort.query(request);
        List<KeywordResponse> keywordResponses = keywordPage.contents().stream()
                .map(keyword -> this.get(keyword.id()))
                .toList();
        return PageResponse.create(keywordResponses,
                keywordPage.currentPage(),
                keywordPage.totalPages(),
                keywordPage.size(),
                keywordPage.totalElements(),
                keywordPage.sortProperties());
    }
}
