package com.leesh.inflpick.v2.token.application.port.in;

import com.leesh.inflpick.v2.token.application.dto.TokenResponse;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredRefreshTokenException;
import com.leesh.inflpick.v2.token.application.port.in.exception.InvalidTokenException;
import com.leesh.inflpick.v2.token.domain.vo.Token;

public interface RefreshTokenUseCase {

    TokenResponse refresh(Token refreshToken) throws ExpiredRefreshTokenException, InvalidTokenException;

}
