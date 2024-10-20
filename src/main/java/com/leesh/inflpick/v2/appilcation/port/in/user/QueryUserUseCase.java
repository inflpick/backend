package com.leesh.inflpick.v2.appilcation.port.in.user;

import com.leesh.inflpick.v2.appilcation.service.user.exception.UserNotFoundException;
import com.leesh.inflpick.v2.domain.common.dto.OffsetPage;
import com.leesh.inflpick.v2.domain.common.dto.OffsetPageQuery;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface QueryUserUseCase {

    User query(UserId userId) throws UserNotFoundException;

    User query(AuthenticationCode authenticationCode) throws UserNotFoundException;

    User query(Oauth2Info oauth2Info) throws UserNotFoundException;

    OffsetPage<User> query(OffsetPageQuery query);

}
