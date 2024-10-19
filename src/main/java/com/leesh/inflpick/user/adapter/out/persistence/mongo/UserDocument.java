package com.leesh.inflpick.user.adapter.out.persistence.mongo;

import com.leesh.inflpick.user.v2.core.entity.AuthenticationProcess;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@Document(collection = "users")
public class UserDocument implements Persistable<String> {

    @Id
    @Getter
    private String id;
    private String nickname;
    private String email;
    private String profileImageUrl;
    private String role;
    private String oauth2UserId;
    private String oauth2Type;
    private String authenticationStatus;
    private String authenticationCode;
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;

    public static UserDocument from(User user) {
        AuthenticationProcess authenticationProcess = user.getAuthenticationProcess();
        AuthenticationStatus status = authenticationProcess.getStatus();
        AuthenticationCode code = authenticationProcess.getCode();
        return UserDocument.builder()
                .id(user.getId().value())
                .email(user.getEmail().value())
                .nickname(user.getNickname().value())
                .profileImageUrl(user.getProfileImageUrl())
                .role(user.getRole().name())
                .oauth2UserId(user.getOauth2Id())
                .oauth2Type(user.getOauth2Provider().name())
                .authenticationStatus(status.name())
                .authenticationCode(code.value())
                .build();
    }

    public User toEntity() {
        Nickname nickname = Nickname.create(this.nickname);
        UserEmail email = UserEmail.create(this.email);
        Role role = Role.from(this.role);
        Oauth2Provider oauth2Provider = Oauth2Provider.valueOf(this.oauth2Type);
        Oauth2Info oauth2Info = Oauth2Info.create(oauth2UserId, oauth2Provider);
        AuthenticationStatus status = AuthenticationStatus.valueOf(this.authenticationStatus);
        AuthenticationCode code = AuthenticationCode.create(this.authenticationCode);
        User user = User.builder(null, oauth2Info)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .role(role)
                .createdDate(createdDate)
                .createdBy(createdBy)
                .lastModifiedDate(lastModifiedDate)
                .lastModifiedBy(lastModifiedBy)
                .build();
        switch (status) {
            case IN_PROGRESS:
                user.startAuthentication(code);
                break;
            case COMPLETED:
                user.completeAuthentication();
                break;
        }
        return user;
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }

}
