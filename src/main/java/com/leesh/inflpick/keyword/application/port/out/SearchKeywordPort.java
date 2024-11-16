package com.leesh.inflpick.keyword.application.port.out;

import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;

import java.util.List;

public interface SearchKeywordPort {

    List<Keyword> search(KeywordName name);

}
