package com.leesh.inflpick.v2.appilcation.port.in.user;

import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface GetUserUseCase {

    User getUser(UserId userId);

    User getUser(AuthenticationCode authenticationCode);

}
