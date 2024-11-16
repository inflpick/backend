package com.leesh.inflpick.user.application.port.out;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.user.domain.User;
import com.leesh.inflpick.user.domain.vo.AuthenticationCode;
import com.leesh.inflpick.user.domain.vo.Oauth2Info;
import com.leesh.inflpick.user.domain.vo.UserId;

import java.util.Optional;

public interface QueryUserPort {

    Optional<User> query(Oauth2Info oauth2Info);

    Optional<User> query(UserId userId);

    Optional<User> query(AuthenticationCode authenticationCode);

    PageResponse<User> query(PageRequest request);
}
