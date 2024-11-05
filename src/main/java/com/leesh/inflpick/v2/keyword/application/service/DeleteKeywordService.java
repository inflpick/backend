package com.leesh.inflpick.v2.keyword.application.service;

import com.leesh.inflpick.v2.keyword.application.port.in.DeleteKeywordUseCase;
import com.leesh.inflpick.v2.keyword.application.port.out.CommandKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteKeywordService implements DeleteKeywordUseCase {

    private final CommandKeywordPort commandKeywordPort;

    @Override
    public void delete(KeywordId id) {
        commandKeywordPort.delete(id);
    }
}
