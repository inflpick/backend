package com.leesh.inflpick.keyword.application.port.in;

import com.leesh.inflpick.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;

public interface CreateKeywordUseCase {

    KeywordId create(KeywordRequest request) throws AlreadyExistKeywordNameException;
}
