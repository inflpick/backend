package com.leesh.inflpick.v2.token.application.port.in;

import com.leesh.inflpick.v2.token.application.dto.CreateTokenResponse;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredRefreshTokenException;
import com.leesh.inflpick.v2.token.application.port.in.exception.InvalidRefreshTokenException;
import com.leesh.inflpick.v2.token.domain.Token;
import com.leesh.inflpick.v2.user.domain.vo.AuthenticationCode;

public interface CreateTokenUseCase {

    CreateTokenResponse create(AuthenticationCode code) throws ExpiredAuthenticationCodeException;

    CreateTokenResponse refresh(Token refreshToken) throws ExpiredRefreshTokenException, InvalidRefreshTokenException;

}
