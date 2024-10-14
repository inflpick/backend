package com.leesh.inflpick.user.adapter.out.persistence.mongo;

import com.leesh.inflpick.user.adapter.in.web.Oauth2Type;
import com.leesh.inflpick.user.core.domain.*;
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
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;

    public static UserDocument from(User user) {
        return UserDocument.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .role(user.getRole().name())
                .oauth2UserId(user.getOauth2UserId())
                .oauth2Type(user.getOauth2Type())
                .build();
    }

    public User toEntity() {
        Nickname nickname = Nickname.from(this.nickname);
        UserEmail email = UserEmail.from(this.email);
        Role role = Role.from(this.role);
        Oauth2Type oauth2Type = Oauth2Type.valueOf(this.oauth2Type);
        Oauth2UserInfo oauth2UserInfo = Oauth2UserInfo.of(oauth2UserId, oauth2Type);
        return User.builder()
                .id(id)
                .nickname(nickname)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .role(role)
                .oauth2UserInfo(oauth2UserInfo)
                .createdDate(createdDate)
                .createdBy(createdBy)
                .lastModifiedDate(lastModifiedDate)
                .lastModifiedBy(lastModifiedBy)
                .build();
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }

}
