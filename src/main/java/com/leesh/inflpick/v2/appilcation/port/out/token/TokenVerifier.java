package com.leesh.inflpick.v2.appilcation.port.out.token;

import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;

public interface TokenVerifier {

    Boolean verify(Token token, TokenType type);

}
