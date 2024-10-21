package com.leesh.inflpick.v2.user.domain;

import com.leesh.inflpick.v2.user.domain.vo.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Builder(access = AccessLevel.PUBLIC, builderMethodName = "requiredBuilder")
public final class User {

    @Getter
    @Builder.Default
    private final UserId id = UserId.empty();
    @Getter
    private final Nickname nickname;
    @Getter
    private final Oauth2Info oauth2Info;
    @Getter
    @Builder.Default
    private final String profileImageUrl = "";
    @Getter
    @Builder.Default
    private final Role role = Role.USER;
    @Getter
    @Builder.Default
    private final UserEmail email = UserEmail.empty();
    @Getter
    @Builder.Default
    private AuthenticationProcess authenticationProcess = AuthenticationProcess.notStarted();
    @Getter
    @Builder.Default
    private final Instant createdDate = Instant.MIN;
    @Builder.Default
    private final String createdBy = "";
    @Builder.Default
    private final Instant lastModifiedDate = Instant.MIN;
    @Builder.Default
    private final String lastModifiedBy = "";

    public static UserBuilder builder(Nickname nickname, Oauth2Info oauth2Info) {
        return requiredBuilder()
                .nickname(nickname)
                .oauth2Info(oauth2Info);
    }

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

    public String getOauth2Id() {
        return oauth2Info.getId();
    }

    public Oauth2Provider getOauth2Provider() {
        return oauth2Info.getProvider();
    }

    public AuthenticationProcess startAuthentication(AuthenticationCode code) {
        if (!authenticationProcess.canStart()) {
            throw new IllegalStateException("Authentication process is already started or completed");
        }
        this.authenticationProcess.start(code);
        return this.authenticationProcess;
    }

    public void completeAuthentication() {
        if (!authenticationProcess.isProgress()) {
            throw new IllegalStateException("Authentication process is not in progress");
        }
        this.authenticationProcess.complete();
    }

    public Boolean isAuthenticationInProgress() {
        return authenticationProcess.isProgress();
    }

    public boolean isPersisted() {
        return !id.isEmpty();
    }

    public static User withId(UserId id, User user) {
        return User.builder(user.nickname, user.oauth2Info)
                .id(id)
                .profileImageUrl(user.profileImageUrl)
                .role(user.role)
                .email(user.email)
                .authenticationProcess(user.authenticationProcess)
                .createdDate(user.createdDate)
                .createdBy(user.createdBy)
                .lastModifiedDate(user.lastModifiedDate)
                .lastModifiedBy(user.lastModifiedBy)
                .build();
    }
}
