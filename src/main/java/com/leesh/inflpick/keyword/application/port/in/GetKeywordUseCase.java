package com.leesh.inflpick.keyword.application.port.in;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;

public interface GetKeywordUseCase {

    KeywordResponse get(KeywordId id);

    PageResponse<KeywordResponse> getPage(PageRequest request);

}
