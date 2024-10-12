package com.leesh.inflpick.user.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Builder
public class User {

    @Getter
    private final String id;
    private final Nickname nickname;
    @Getter
    private final String profileImageUrl;
    @Getter
    private final Role role;
    private final Oauth2UserInfo oauth2UserInfo;
    @Getter
    private final Instant createdDate;
    private final String createdBy;
    private final Instant lastModifiedDate;
    private final String lastModifiedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getNickname() {
        return nickname.name();
    }

    public String getOauth2UserId() {
        return oauth2UserInfo.id();
    }

    public String getOauth2Type() {
        return oauth2UserInfo.oauth2Type().name();
    }
}
