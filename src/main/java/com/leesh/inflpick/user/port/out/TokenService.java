package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.user.v2.core.entity.User;

public interface TokenService {

    Token createAccessToken(User user);

    Token createRefreshToken(User user);

    Boolean verifyToken(Token token, TokenType type);

    User extractToken(Token token);

    Token createAccessToken(Token refreshToken);
}
