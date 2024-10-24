package com.leesh.inflpick.v2.token.application.service;

import com.leesh.inflpick.v2.token.application.port.in.ValidateTokenUseCase;
import com.leesh.inflpick.v2.token.adapter.out.TokenValidator;
import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ValidateTokenService implements ValidateTokenUseCase {

    private final TokenValidator tokenValidator;

    @Override
    public Boolean isValid(Token token, TokenType type) {
        return tokenValidator.verify(token, type);
    }

    @Override
    public Boolean isExpired(Token token) {
        return tokenValidator.isExpired(token);
    }
}
