package com.leesh.inflpick.v2.keyword.application.port.in;

import com.leesh.inflpick.v2.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.v2.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;

public interface CreateKeywordUseCase {

    KeywordId create(KeywordRequest request) throws AlreadyExistKeywordNameException;
}
