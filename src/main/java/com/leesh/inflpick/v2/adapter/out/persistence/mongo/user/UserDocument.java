package com.leesh.inflpick.v2.adapter.out.persistence.mongo.user;

import com.leesh.inflpick.v2.domain.user.AuthenticationProcess;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@Document(collection = "users")
@Getter
class UserDocument {

    @Id
    private String id;
    private String nickname;
    private String email;
    private String profileImageUrl;
    private String role;
    private String oauth2Id;
    private String oauth2Provider;
    @Builder.Default
    private String authenticationStatus = AuthenticationStatus.NOT_STARTED.name();
    @Builder.Default
    private String authenticationCode = AuthenticationCode.empty().value();
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;

    static UserDocument from(User user) {
        AuthenticationProcess authenticationProcess = user.getAuthenticationProcess();
        AuthenticationStatus status = authenticationProcess.getStatus();
        AuthenticationCode code = authenticationProcess.getCode();
        String userId = user.isPersisted() ? user.getId().getValue() : null;
        return UserDocument.builder()
                .id(userId)
                .email(user.getEmail().value())
                .nickname(user.getNickname().value())
                .profileImageUrl(user.getProfileImageUrl())
                .role(user.getRole().name())
                .oauth2Id(user.getOauth2Id())
                .oauth2Provider(user.getOauth2Provider().name())
                .authenticationStatus(status.name())
                .authenticationCode(code.value())
                .build();
    }

    User toEntity() {
        Nickname nickname = Nickname.create(this.nickname);
        UserEmail email = UserEmail.create(this.email);
        Role role = Role.from(this.role);
        Oauth2Provider oauth2Provider = Oauth2Provider.valueOf(this.oauth2Provider);
        Oauth2Info oauth2Info = Oauth2Info.create(oauth2Id, oauth2Provider);
        AuthenticationStatus status = AuthenticationStatus.valueOf(this.authenticationStatus);
        AuthenticationCode code = AuthenticationCode.create(this.authenticationCode);
        UserId userId = UserId.create(id);
        User user = User.builder(nickname, oauth2Info)
                .id(userId)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .role(role)
                .createdDate(createdDate)
                .createdBy(createdBy)
                .lastModifiedDate(lastModifiedDate)
                .lastModifiedBy(lastModifiedBy)
                .build();
        if (status == AuthenticationStatus.IN_PROGRESS) {
            user.startAuthentication(code);
        }
        return user;
    }

}
