package com.leesh.inflpick.v2.application.service.token;

import com.leesh.inflpick.v2.application.port.in.token.ExtractTokenUseCase;
import com.leesh.inflpick.v2.application.port.out.token.TokenExtractor;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
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
