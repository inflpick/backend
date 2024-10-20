package com.leesh.inflpick.v2.appilcation.port.in.auth;

import com.leesh.inflpick.v2.domain.auth.vo.Token;
import com.leesh.inflpick.v2.domain.auth.vo.TokenType;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface TokenUseCase {

    Token createAccessToken(UserId userId);

    Token createRefreshToken(UserId userId);

    boolean verifyToken(Token token, TokenType tokenType);

    User extractToken(Token token);
}
