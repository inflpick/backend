package com.leesh.inflpick.v2.appilcation.port.in.token;

import com.leesh.inflpick.v2.appilcation.dto.token.TokenResponse;
import com.leesh.inflpick.v2.appilcation.dto.user.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.appilcation.port.in.token.exception.ExpiredAuthenticationCodeException;

public interface CreateTokenUseCase {

    TokenResponse create(AuthenticationCodeTokenRequest request) throws ExpiredAuthenticationCodeException;

}
