package com.leesh.inflpick.v2.keyword.application.port.out;

import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

public interface CommandKeywordPort {

    KeywordId save(Keyword keyword);

    void delete(KeywordName name);

    void delete(KeywordId id);
}
