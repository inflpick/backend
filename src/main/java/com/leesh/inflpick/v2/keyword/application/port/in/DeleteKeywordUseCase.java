package com.leesh.inflpick.v2.keyword.application.port.in;

import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;

public interface DeleteKeywordUseCase {

    void delete(KeywordId id);
}
