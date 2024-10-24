package com.leesh.inflpick.v2.user.application.port.out;

import com.leesh.inflpick.v2.shared.application.dto.OffsetPageQuery;
import com.leesh.inflpick.v2.shared.application.dto.OffsetPage;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.AuthenticationCode;
import com.leesh.inflpick.v2.user.domain.vo.Oauth2Info;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

import java.util.Optional;

public interface QueryUserPort {

    Optional<User> query(Oauth2Info oauth2Info);

    Optional<User> query(UserId userId);

    Optional<User> query(AuthenticationCode authenticationCode);

    OffsetPage<User> query(OffsetPageQuery request);
}
