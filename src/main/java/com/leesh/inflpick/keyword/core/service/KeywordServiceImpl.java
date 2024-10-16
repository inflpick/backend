package com.leesh.inflpick.keyword.core.service;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import com.leesh.inflpick.keyword.port.in.DuplicateKeywordNameException;
import com.leesh.inflpick.keyword.port.in.KeywordCommand;
import com.leesh.inflpick.keyword.port.in.KeywordCommandService;
import com.leesh.inflpick.keyword.port.in.KeywordReadService;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Builder
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class KeywordServiceImpl implements KeywordCommandService, KeywordReadService {

    private final UuidHolder uuidHolder;
    private final KeywordRepository keywordRepository;

    @Transactional
    @Override
    public String create(KeywordCommand command) {
        verifyDuplicatedName(command);
        Keyword entity = command.toEntity(uuidHolder);
        keywordRepository.save(entity);
        return keywordRepository.getById(entity.getId())
                .getId();
    }

    @Override
    public void update(KeywordCommand command, KeywordName keywordName) {
        verifyDuplicatedName(command);
        keywordRepository.findByName(keywordName)
                .ifPresent(keyword -> {
                    keyword.update(command.name(), command.color());
                    keywordRepository.save(keyword);
                });
    }

    @Override
    public void delete(KeywordName keywordName) {
        keywordRepository.deleteByName(keywordName);
    }

    @Override
    public List<Keyword> search(KeywordName keywordName) {
        return keywordRepository.search(keywordName);
    }

    private void verifyDuplicatedName(KeywordCommand command) {
        keywordRepository.findByName(command.name())
                .ifPresent(keyword -> {
                    throw new DuplicateKeywordNameException("키워드 이름", command.name().name(), "Keyword");
                });
    }
}
