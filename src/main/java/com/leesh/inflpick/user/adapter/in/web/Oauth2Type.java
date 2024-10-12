package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.user.port.out.NotSupportedOauth2TypeException;

import java.util.Arrays;
import java.util.List;

public enum Oauth2Type {

    KAKAO,
    GOOGLE,
    NAVER,
    ;

    public static List<String> availableOauth2Types() {
        return Arrays.stream(Oauth2Type.values())
                .map(Oauth2Type::name)
                .toList();
    }

    public static Oauth2Type from(String oauth2Type) {
        try {
            return Oauth2Type.valueOf(oauth2Type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotSupportedOauth2TypeException(oauth2Type);
        }
    }

}
