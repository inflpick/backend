package com.leesh.inflpick.v2.application.service.token;

import com.leesh.inflpick.v2.application.port.in.token.ValidateTokenUseCase;
import com.leesh.inflpick.v2.application.port.out.token.TokenValidator;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
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
