package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.AuthenticationCode;
import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Info;

import java.util.Optional;

public interface UserRepository {

    String save(User user);

    long count();

    User getById(String id) throws UserNotFoundException;

    Optional<User> findByOauth2UserInfo(Oauth2Info oauth2Info);

    PageResponse<User> getPage(PageRequest query);

    Optional<User> findByAuthenticationCode(AuthenticationCode authenticationCode);
}
