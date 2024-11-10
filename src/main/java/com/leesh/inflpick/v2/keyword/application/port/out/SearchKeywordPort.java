package com.leesh.inflpick.v2.keyword.application.port.out;

import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

import java.util.List;

public interface SearchKeywordPort {

    List<Keyword> search(KeywordName name);

}
