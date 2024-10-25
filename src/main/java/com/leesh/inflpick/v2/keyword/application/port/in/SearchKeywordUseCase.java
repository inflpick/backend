package com.leesh.inflpick.v2.keyword.application.port.in;

import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;

import java.util.List;
import java.util.Set;

public interface SearchKeywordUseCase {

    List<Keyword> search(KeywordName keywordName);

}
