package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Provider;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface Oauth2UserConverter {

    Boolean isSupport(Oauth2Provider oauth2Provider);

    OAuth2User convert(OAuth2User oAuth2User, String userNameAttributeName);
}
