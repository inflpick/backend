package com.leesh.inflpick.keyword.application.service;

import com.leesh.inflpick.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.keyword.application.exception.KeywordNotFoundException;
import com.leesh.inflpick.keyword.application.port.in.UpdateKeywordUseCase;
import com.leesh.inflpick.keyword.application.port.out.CommandKeywordPort;
import com.leesh.inflpick.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.exception.KeywordNameFormatException;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateKeywordService implements UpdateKeywordUseCase {

    private final QueryKeywordPort queryKeywordPort;
    private final CommandKeywordPort commandKeywordPort;

    @Override
    public void update(KeywordId id, KeywordRequest request) throws AlreadyExistKeywordNameException, KeywordNameFormatException {

        var keyword = queryKeywordPort.query(id)
                .orElseThrow(() -> new KeywordNotFoundException(id));

        validateExistDuplicatedKeywordName(request);
        Keyword updatedKeyword = keyword.update(request);
        commandKeywordPort.save(updatedKeyword);
    }

    private void validateExistDuplicatedKeywordName(KeywordRequest request) {
        KeywordName keywordName = KeywordName.create(request.name());
        queryKeywordPort.query(keywordName).ifPresent(k -> {
            throw new AlreadyExistKeywordNameException(k.name());
        });
    }
}
