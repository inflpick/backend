package com.leesh.inflpick.v2.application.port.out.token;

import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;

public interface TokenValidator {

    Boolean verify(Token token, TokenType type);

    Boolean isExpired(Token token);

}
