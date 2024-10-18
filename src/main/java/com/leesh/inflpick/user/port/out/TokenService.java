package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.user.core.domain.User;

public interface TokenService {

    Token createAccessToken(User user);

    Token createRefreshToken(User user);

    Boolean verifyToken(Token token, TokenType type);

    User extractToken(Token token);

    Token createAccessToken(Token refreshToken);
}
