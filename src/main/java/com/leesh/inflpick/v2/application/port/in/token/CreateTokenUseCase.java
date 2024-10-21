package com.leesh.inflpick.v2.application.port.in.token;

import com.leesh.inflpick.v2.application.dto.token.TokenResponse;
import com.leesh.inflpick.v2.application.dto.user.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.application.port.in.token.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.v2.application.port.in.token.exception.InvalidTokenException;

public interface CreateTokenUseCase {

    TokenResponse create(AuthenticationCodeTokenRequest request) throws InvalidTokenException, ExpiredAuthenticationCodeException;

}
