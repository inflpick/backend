package com.leesh.inflpick.v2.appilcation.port.in.token;

import com.leesh.inflpick.v2.appilcation.dto.token.TokenResponse;
import com.leesh.inflpick.v2.appilcation.port.in.token.exception.ExpiredRefreshTokenException;
import com.leesh.inflpick.v2.appilcation.port.in.token.exception.InvalidTokenException;
import com.leesh.inflpick.v2.domain.token.vo.Token;

public interface RefreshTokenUseCase {

    TokenResponse refresh(Token refreshToken) throws ExpiredRefreshTokenException, InvalidTokenException;

}
