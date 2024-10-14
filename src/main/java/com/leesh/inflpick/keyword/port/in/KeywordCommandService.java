package com.leesh.inflpick.keyword.port.in;

import com.leesh.inflpick.keyword.core.domain.KeywordName;

public interface KeywordCommandService {

    String create(KeywordCommand command);

    void update(KeywordCommand command, KeywordName keywordName);

    void delete(KeywordName keywordName);
}
