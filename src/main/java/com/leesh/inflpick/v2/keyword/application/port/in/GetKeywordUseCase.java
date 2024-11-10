package com.leesh.inflpick.v2.keyword.application.port.in;

import com.leesh.inflpick.v2.common.application.dto.PageRequest;
import com.leesh.inflpick.v2.common.application.dto.PageResponse;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;

public interface GetKeywordUseCase {

    KeywordResponse get(KeywordId id);

    PageResponse<KeywordResponse> getPage(PageRequest request);

}
