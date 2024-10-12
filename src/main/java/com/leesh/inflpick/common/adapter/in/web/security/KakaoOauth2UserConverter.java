package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.in.web.Oauth2Type;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class KakaoOauth2UserConverter implements Oauth2UserConverter{

    @Override
    public Boolean isSupport(Oauth2Type oauth2Type) {
        return Oauth2Type.KAKAO.equals(oauth2Type);
    }

    @Override
    public OAuth2User convert(OAuth2User oAuth2User, String userNameAttributeName) {
        return KakaoUser.from(oAuth2User, userNameAttributeName);
    }
}
