package com.leesh.inflpick.v2.token.application.service;

import com.leesh.inflpick.v2.token.application.port.in.ExtractTokenUseCase;
import com.leesh.inflpick.v2.token.adapter.out.TokenExtractor;
import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ExtractTokenService implements ExtractTokenUseCase {

    private final TokenExtractor extractor;

    @Override
    public UserId extract(Token token) {
        return extractor.extract(token);
    }
}
