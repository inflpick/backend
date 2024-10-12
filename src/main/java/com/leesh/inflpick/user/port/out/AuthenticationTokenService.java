package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.user.adapter.out.jwt.JwtAuthentication;
import com.leesh.inflpick.user.core.domain.User;

public interface AuthenticationTokenService {

    AuthenticationToken createAccessToken(User user);

    AuthenticationToken createRefreshToken(User user);

    Boolean verifyToken(AuthenticationToken token);

    User extractToken(JwtAuthentication jwtAuthentication);
}
