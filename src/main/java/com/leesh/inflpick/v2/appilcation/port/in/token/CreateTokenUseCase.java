package com.leesh.inflpick.v2.appilcation.port.in.token;

import com.leesh.inflpick.v2.appilcation.dto.user.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.appilcation.dto.user.TokenResponse;

public interface CreateTokenUseCase {

    TokenResponse create(AuthenticationCodeTokenRequest request);

}
