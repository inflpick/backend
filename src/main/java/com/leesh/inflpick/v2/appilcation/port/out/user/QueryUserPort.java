package com.leesh.inflpick.v2.appilcation.port.out.user;

import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

import java.util.Optional;

public interface QueryUserPort {

    Optional<User> query(Oauth2Info oauth2Info);

    Optional<User> query(UserId userId);

    Optional<User> query(AuthenticationCode authenticationCode);

    OffsetPageResponse<User> query(OffsetPageRequest request);
}
