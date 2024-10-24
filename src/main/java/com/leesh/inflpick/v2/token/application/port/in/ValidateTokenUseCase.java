package com.leesh.inflpick.v2.token.application.port.in;

import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;

public interface ValidateTokenUseCase {

    Boolean isValid(Token token, TokenType type);

    Boolean isExpired(Token token);

}
