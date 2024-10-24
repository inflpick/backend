package com.leesh.inflpick.v2.token.application.port.in;

import com.leesh.inflpick.v2.token.application.dto.TokenResponse;
import com.leesh.inflpick.v2.token.application.dto.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.v2.token.application.port.in.exception.InvalidTokenException;

public interface CreateTokenUseCase {

    TokenResponse create(AuthenticationCodeTokenRequest request) throws InvalidTokenException, ExpiredAuthenticationCodeException;

}
