package com.leesh.inflpick.user.port.in;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.AuthenticationCode;
import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Info;

import java.util.Optional;

public interface UserQueryService {

    Optional<User> query(Oauth2Info oauth2Info);

    User query(String id);

    User query(AuthenticationCode authenticationCode);

    PageResponse<User> query(PageRequest request);
}
