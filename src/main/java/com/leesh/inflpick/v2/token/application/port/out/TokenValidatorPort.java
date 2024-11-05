package com.leesh.inflpick.v2.token.application.port.out;

import com.leesh.inflpick.v2.token.domain.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;

public interface TokenValidatorPort {

    Boolean isValid(Token token, TokenType type);

    Boolean isExpired(Token token);

}
