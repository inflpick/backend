package com.leesh.inflpick.keyword.port.in;

import com.leesh.inflpick.keyword.core.domain.Keyword;

public interface KeywordCreateService {

    Keyword create(KeywordCreateCommand command);

}
