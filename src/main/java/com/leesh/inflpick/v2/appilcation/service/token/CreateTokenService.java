package com.leesh.inflpick.v2.appilcation.service.token;

import com.leesh.inflpick.v2.appilcation.port.in.token.CreateTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.out.token.TokenGenerator;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateTokenService implements CreateTokenUseCase {

    private final TokenGenerator tokenGenerator;

    @Override
    public Token create(UserId userId, TokenType type) {
        return tokenGenerator.generate(userId, type);
    }
}
