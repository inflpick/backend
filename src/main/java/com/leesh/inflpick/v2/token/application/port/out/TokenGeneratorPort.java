package com.leesh.inflpick.v2.token.application.port.out;

import com.leesh.inflpick.v2.token.domain.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface TokenGeneratorPort {

    Token generate(UserId userId, TokenType type);

}
