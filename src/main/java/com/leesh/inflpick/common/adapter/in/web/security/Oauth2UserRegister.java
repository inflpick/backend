package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.in.web.Oauth2Type;
import com.leesh.inflpick.user.core.domain.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface Oauth2UserRegister {

    Boolean isSupport(Oauth2Type oauth2Type);

    User register(OAuth2User oAuth2User, String userNameAttributeName);
}
