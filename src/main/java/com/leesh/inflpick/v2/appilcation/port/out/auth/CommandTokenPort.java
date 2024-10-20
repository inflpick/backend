package com.leesh.inflpick.v2.appilcation.port.out.auth;

import com.leesh.inflpick.v2.domain.auth.vo.Token;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface CommandTokenPort {

    Token createAccessToken(UserId userId);



}
