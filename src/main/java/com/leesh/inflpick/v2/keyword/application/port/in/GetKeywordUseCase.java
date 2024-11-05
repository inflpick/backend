package com.leesh.inflpick.v2.keyword.application.port.in;

import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;

public interface GetKeywordUseCase {

    KeywordResponse get(KeywordId id);

    OffsetPageResponse<KeywordResponse> getPage(OffsetPageRequest request);

}
