package com.leesh.inflpick.v2.token.adapter.out;

import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;

public interface TokenValidator {

    Boolean verify(Token token, TokenType type);

    Boolean isExpired(Token token);

}
