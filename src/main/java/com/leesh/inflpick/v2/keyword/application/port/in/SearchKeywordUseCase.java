package com.leesh.inflpick.v2.keyword.application.port.in;

import com.leesh.inflpick.v2.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

import java.util.List;

public interface SearchKeywordUseCase {

    List<KeywordResponse> search(KeywordName keywordName);

}
