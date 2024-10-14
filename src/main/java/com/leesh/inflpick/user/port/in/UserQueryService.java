package com.leesh.inflpick.user.port.in;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.UserSortType;

import java.util.Collection;
import java.util.Optional;

public interface UserQueryService {

    Optional<User> getOauth2User(Oauth2UserInfo oauth2UserInfo);

    User getById(String id);

    PageDetails<Collection<User>> getPage(PageQuery<UserSortType> pageQuery);
}
