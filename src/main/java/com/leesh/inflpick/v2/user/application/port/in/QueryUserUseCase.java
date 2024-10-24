package com.leesh.inflpick.v2.user.application.port.in;

import com.leesh.inflpick.v2.user.application.port.in.exception.UserNotFoundException;
import com.leesh.inflpick.v2.shared.application.dto.OffsetPage;
import com.leesh.inflpick.v2.shared.application.dto.OffsetPageQuery;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.AuthenticationCode;
import com.leesh.inflpick.v2.user.domain.vo.Oauth2Info;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface QueryUserUseCase {

    User query(UserId userId) throws UserNotFoundException;

    User query(AuthenticationCode authenticationCode) throws UserNotFoundException;

    User query(Oauth2Info oauth2Info) throws UserNotFoundException;

    OffsetPage<User> query(OffsetPageQuery query);

}
