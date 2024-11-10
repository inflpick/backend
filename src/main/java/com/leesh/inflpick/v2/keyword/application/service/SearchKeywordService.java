package com.leesh.inflpick.v2.keyword.application.service;

import com.leesh.inflpick.v2.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.v2.keyword.application.port.in.SearchKeywordUseCase;
import com.leesh.inflpick.v2.keyword.application.port.out.SearchKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class SearchKeywordService implements SearchKeywordUseCase {

    private final SearchKeywordPort searchKeywordPort;

    @Override
    public List<KeywordResponse> search(KeywordName keywordName) {
        return searchKeywordPort.search(keywordName).stream()
                .map(KeywordResponse::create)
                .toList();
    }
}
