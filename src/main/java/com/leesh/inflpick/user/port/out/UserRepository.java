package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.user.core.domain.AuthenticationCode;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;

import java.util.Optional;

public interface UserRepository {

    String save(User user);

    long count();

    User getById(String id) throws UserNotFoundException;

    Optional<User> findByOauth2UserInfo(Oauth2UserInfo oauth2UserInfo);

    PageResponse<User> getPage(PageRequest query);

    Optional<User> findByAuthenticationCode(AuthenticationCode authenticationCode);
}
