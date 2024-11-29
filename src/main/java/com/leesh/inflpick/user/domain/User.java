package com.leesh.inflpick.user.domain;

import com.leesh.inflpick.user.domain.vo.*;

import java.time.Instant;
import java.util.Objects;

public final class User {
    private final UserId id;
    private final Nickname nickname;
    private final Oauth2Info oauth2Info;
    private final String profileImageUrl;
    private final Role role;
    private final UserEmail email;
    private final AuthenticationCode authenticationCode;
    private final Instant createdDate;
    private final String createdBy;
    private final Instant lastModifiedDate;
    private final String lastModifiedBy;

    public User(UserId id,
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
        this.id = id;
        this.nickname = nickname;
        this.oauth2Info = oauth2Info;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
        this.email = email;
        this.authenticationCode = authenticationCode;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }

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

    public UserId id() {
        return id;
    }

    public Nickname nickname() {
        return nickname;
    }

    public Oauth2Info oauth2Info() {
        return oauth2Info;
    }

    public String profileImageUrl() {
        return profileImageUrl;
    }

    public Role role() {
        return role;
    }

    public UserEmail email() {
        return email;
    }

    public AuthenticationCode authenticationCode() {
        return authenticationCode;
    }

    public Instant createdDate() {
        return createdDate;
    }

    public String createdBy() {
        return createdBy;
    }

    public Instant lastModifiedDate() {
        return lastModifiedDate;
    }

    public String lastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
