package com.leesh.inflpick.user.core.domain;

import com.leesh.inflpick.user.adapter.in.web.Oauth2Type;

public record Oauth2UserInfo(String id, Oauth2Type oauth2Type) {
    public static Oauth2UserInfo of(String id, Oauth2Type oauth2Type) {
        return new Oauth2UserInfo(id, oauth2Type);
    }
}
