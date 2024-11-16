package com.leesh.inflpick.keyword.application.port.in;

import com.leesh.inflpick.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;

import java.util.List;

public interface SearchKeywordUseCase {

    List<KeywordResponse> search(KeywordName keywordName);

}
