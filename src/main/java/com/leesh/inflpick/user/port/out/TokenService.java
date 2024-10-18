package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.user.adapter.out.jwt.Jwt;
import com.leesh.inflpick.user.core.domain.User;

public interface TokenService {

    Token createAccessToken(User user);

    Token createRefreshToken(User user);

    Boolean verifyToken(Token token);

    User extractToken(Jwt jwtAuthentication);
}
