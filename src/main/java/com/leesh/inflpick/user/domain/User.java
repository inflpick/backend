package com.leesh.inflpick.user.domain;

import com.leesh.inflpick.user.domain.vo.*;

import java.time.Instant;

public record User(UserId id,
                   Nickname nickname,
                   Oauth2Info oauth2Info,
                   String profileImageUrl,
                   Role role,
                   UserEmail email,
                   AuthenticationCode authenticationCode,
                   Instant createdDate,
                   String createdBy,
                   Instant lastModifiedDate,
                   String lastModifiedBy) {

    public String getOauth2Id() {
        return oauth2Info.getId();
    }

    public Oauth2Provider getOauth2Provider() {
        return oauth2Info.getProvider();
    }

    public User startAuthentication(AuthenticationCode code) {
        return User.withId(id,
                nickname,
                oauth2Info,
                profileImageUrl,
                role,
                email,
                code,
                createdDate,
                createdBy,
                lastModifiedDate,
                lastModifiedBy);
    }

    public boolean isPersisted() {
        return id != null && !id.isEmpty();
    }

    public static User withId(UserId id,
                              Nickname nickname,
                              Oauth2Info oauth2Info,
                              String profileImageUrl,
                              Role role,
                              UserEmail email,
                              AuthenticationCode authenticationCode,
                              Instant createdDate,
                              String createdBy,
                              Instant lastModifiedDate,
                              String lastModifiedBy) {
        return new User(id, nickname, oauth2Info, profileImageUrl, role, email, authenticationCode, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
    }

    public static User withoutId(String nickname,
                                 String oauth2Id,
                                 Oauth2Provider oauth2Provider,
                                 String profileImageUrl,
                                 Role role,
                                 String email) {
        UserId emptyId = UserId.empty();
        Nickname userNickname = Nickname.create(nickname);
        UserEmail userEmail = UserEmail.create(email);
        Oauth2Info oauth2Info = Oauth2Info.create(oauth2Id, oauth2Provider);
        AuthenticationCode emptyCode = AuthenticationCode.empty();
        return new User(emptyId, userNickname, oauth2Info, profileImageUrl, role, userEmail, emptyCode, null, null, null, null);
    }

    public User endAuthenticate() {
        return User.withId(id,
                nickname,
                oauth2Info,
                profileImageUrl,
                role,
                email,
                AuthenticationCode.empty(),
                createdDate,
                createdBy,
                lastModifiedDate,
                lastModifiedBy);
    }
}
