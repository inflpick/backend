package com.leesh.inflpick.keyword.application.port.in;

import com.leesh.inflpick.keyword.domain.vo.KeywordId;

public interface DeleteKeywordUseCase {

    void delete(KeywordId id);
}
