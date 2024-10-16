package com.leesh.inflpick.user.port.in;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;

import java.util.Optional;

public interface UserQueryService {

    Optional<User> query(Oauth2UserInfo oauth2UserInfo);

    User query(String id);

    PageResponse<User> query(PageRequest request);
}
