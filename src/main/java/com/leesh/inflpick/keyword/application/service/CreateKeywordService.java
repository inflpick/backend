package com.leesh.inflpick.keyword.application.service;

import com.leesh.inflpick.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.keyword.application.port.in.CreateKeywordUseCase;
import com.leesh.inflpick.keyword.application.port.out.CommandKeywordPort;
import com.leesh.inflpick.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateKeywordService implements CreateKeywordUseCase {

    private final CommandKeywordPort commandKeywordPort;
    private final QueryKeywordPort queryKeywordPort;

    @Override
    public KeywordId create(KeywordRequest request) throws AlreadyExistKeywordNameException {
        KeywordName keywordName = KeywordName.create(request.name());
        AtomicReference<KeywordId> keywordId = new AtomicReference<>();
        queryKeywordPort.query(keywordName)
                .ifPresentOrElse(
                        keyword -> {
                            throw new AlreadyExistKeywordNameException(keywordName.name());
                        },
                        () -> {
                            Keyword entity = request.toEntity();
                            KeywordId saved = commandKeywordPort.save(entity);
                            keywordId.set(saved);
                        }
                );
        return keywordId.get();
    }
}
