package com.leesh.inflpick.keyword.application.port.in;

import com.leesh.inflpick.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.keyword.application.exception.KeywordNotFoundException;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;

public interface UpdateKeywordUseCase {

    void update(KeywordId id, KeywordRequest request) throws AlreadyExistKeywordNameException, KeywordNotFoundException;
}
