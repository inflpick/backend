package com.leesh.inflpick.keyword.port.in;

import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordName;

import java.util.List;

public interface KeywordReadService {

    List<Keyword> search(KeywordName keywordName);

}
