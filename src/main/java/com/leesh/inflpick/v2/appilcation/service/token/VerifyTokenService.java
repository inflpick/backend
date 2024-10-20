package com.leesh.inflpick.v2.appilcation.service.token;

import com.leesh.inflpick.v2.appilcation.port.in.token.VerifyTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.out.token.TokenVerifier;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class VerifyTokenService implements VerifyTokenUseCase {

    private final TokenVerifier tokenVerifier;

    @Override
    public Boolean verify(Token token, TokenType type) {
        return tokenVerifier.verify(token, type);
    }
}
