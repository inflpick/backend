package com.leesh.inflpick.v2.domain.user.vo;

import com.leesh.inflpick.v2.domain.user.exception.NotSupportedOauth2TypeException;

import java.util.Arrays;
import java.util.List;

public enum Oauth2Provider {

    KAKAO,
    GOOGLE,
    NAVER,
    ;

    public static List<String> availableOauth2Types() {
        return Arrays.stream(Oauth2Provider.values())
                .map(Oauth2Provider::name)
                .toList();
    }

    public static Oauth2Provider from(String oauth2Type) {
        try {
            return Oauth2Provider.valueOf(oauth2Type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotSupportedOauth2TypeException("Not supported oauth2 type: " + oauth2Type);
        }
    }

}
