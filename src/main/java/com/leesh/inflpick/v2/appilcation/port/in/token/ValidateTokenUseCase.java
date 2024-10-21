package com.leesh.inflpick.v2.appilcation.port.in.token;

import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;

public interface ValidateTokenUseCase {

    Boolean verify(Token token, TokenType type);

    Boolean isExpired(Token token);

}
