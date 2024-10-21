package com.leesh.inflpick.v2.appilcation.port.in.user;

import com.leesh.inflpick.v2.domain.user.AuthenticationProcess;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface AuthenticateUserUseCase {

    AuthenticationProcess authenticate(UserId userId);

}
