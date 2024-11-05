package com.leesh.inflpick.v2.keyword.application.port.in;

import com.leesh.inflpick.v2.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.v2.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.v2.keyword.application.exception.KeywordNotFoundException;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;

public interface UpdateKeywordUseCase {

    void update(KeywordId id, KeywordRequest request) throws AlreadyExistKeywordNameException, KeywordNotFoundException;
}
