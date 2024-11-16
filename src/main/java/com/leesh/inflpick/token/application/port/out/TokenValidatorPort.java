package com.leesh.inflpick.token.application.port.out;

import com.leesh.inflpick.token.domain.Token;
import com.leesh.inflpick.token.domain.vo.TokenType;

public interface TokenValidatorPort {

    Boolean isValid(Token token, TokenType type);

    Boolean isExpired(Token token);

}
