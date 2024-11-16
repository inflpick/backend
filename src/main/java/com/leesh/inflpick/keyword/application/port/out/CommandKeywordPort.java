package com.leesh.inflpick.keyword.application.port.out;

import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;

public interface CommandKeywordPort {

    KeywordId save(Keyword keyword);

    void delete(KeywordName name);

    void delete(KeywordId id);
}
