package com.leesh.inflpick.v2.token.adapter.out;

import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface TokenGenerator {

    Token generate(UserId userId, TokenType type);

}
