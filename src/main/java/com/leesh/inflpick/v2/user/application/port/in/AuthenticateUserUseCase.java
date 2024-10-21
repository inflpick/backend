package com.leesh.inflpick.v2.user.application.port.in;

import com.leesh.inflpick.v2.user.domain.AuthenticationProcess;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface AuthenticateUserUseCase {

    AuthenticationProcess authenticate(UserId userId);

}
