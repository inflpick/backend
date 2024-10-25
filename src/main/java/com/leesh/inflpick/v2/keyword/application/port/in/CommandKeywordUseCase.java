package com.leesh.inflpick.v2.keyword.application.port.in;

import com.leesh.inflpick.v2.keyword.application.dto.KeywordCommand;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

public interface CommandKeywordUseCase {

    KeywordId create(KeywordCommand command);

    void update(KeywordName name, KeywordCommand command);

    void delete(KeywordName name);
}
