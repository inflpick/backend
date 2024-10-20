package com.leesh.inflpick.v2.adapter.in.security;

import com.leesh.inflpick.v2.domain.user.vo.Oauth2Provider;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface Oauth2UserConverter {

    Boolean isSupport(Oauth2Provider oauth2Provider);

    OAuth2User convert(OAuth2User oAuth2User, String userNameAttributeName);
}
