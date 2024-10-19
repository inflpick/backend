package com.leesh.inflpick.user.v2.core.entity.vo;

import com.leesh.inflpick.user.port.out.NotSupportedOauth2TypeException;

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
            throw new NotSupportedOauth2TypeException(oauth2Type);
        }
    }

}
