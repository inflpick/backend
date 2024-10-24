package com.leesh.inflpick.v2.keyword.application.service;

import com.leesh.inflpick.v2.keyword.application.dto.KeywordCommand;
import com.leesh.inflpick.v2.keyword.application.exception.DuplicateKeywordNameException;
import com.leesh.inflpick.v2.keyword.application.port.in.CommandKeywordUseCase;
import com.leesh.inflpick.v2.keyword.application.port.out.CommandKeywordPort;
import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommandKeywordService implements CommandKeywordUseCase {

    private final QueryKeywordPort queryKeywordPort;
    private final CommandKeywordPort commandKeywordPort;

    @Override
    public KeywordId create(KeywordCommand command) {
        verifyDuplicatedName(command);
        Keyword entity = command.toEntity();
        return commandKeywordPort.save(entity);
    }

    @Override
    public void update(KeywordName name, KeywordCommand command) {
        verifyDuplicatedName(command);
        queryKeywordPort.query(name)
            .ifPresent(keyword -> {
                keyword.update(command.name(), command.color());
                commandKeywordPort.save(keyword);
            });
    }

    @Override
    public void delete(KeywordName name) {
        commandKeywordPort.delete(name);
    }

    private void verifyDuplicatedName(KeywordCommand command) {
        KeywordName name = command.name();
        queryKeywordPort.query(name)
                .ifPresent(keyword -> {
                    throw new DuplicateKeywordNameException("Keyword name is duplicated, name: " + name);
                });
    }
}
