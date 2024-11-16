package com.leesh.inflpick.token.application.port.out;

import com.leesh.inflpick.token.domain.Token;
import com.leesh.inflpick.token.domain.vo.TokenType;
import com.leesh.inflpick.user.domain.vo.UserId;

public interface TokenGeneratorPort {

    Token generate(UserId userId, TokenType type);

}
