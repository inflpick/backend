package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;

import java.util.Optional;

public interface UserRepository {

    String save(User user);

    long count();

    User getById(String uuid);

    void deleteById(String id);

    Optional<User> getByOauth2UserInfo(Oauth2UserInfo oauth2UserInfo);

}
