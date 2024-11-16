package com.leesh.inflpick.user.adapter.out.persistence.mongo;

import com.leesh.inflpick.user.domain.User;
import com.leesh.inflpick.user.domain.vo.*;
import com.leesh.inflpick.v2.user.domain.vo.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "users")
public record UserDocument(@Id String id,
                           String nickname,
                           String email,
                           String profileImageUrl,
                           String role,
                           String oauth2Id,
                           String oauth2Provider,
                           String authenticationCode,
                           @CreatedBy String createdBy,
                           @CreatedDate Instant createdDate,
                           @LastModifiedBy String lastModifiedBy,
                           @LastModifiedDate Instant lastModifiedDate) {

    public static UserDocument from(User user) {
        String userId = user.isPersisted() ? user.id().id() : null;
        return new UserDocument(
                userId,
                user.nickname().nickname(),
                user.email().email(),
                user.profileImageUrl(),
                user.role().name(),
                user.oauth2Info().getId(),
                user.oauth2Info().getProvider().name(),
                user.authenticationCode().code(),
                user.createdBy(),
                user.createdDate(),
                user.lastModifiedBy(),
                user.lastModifiedDate()
        );
    }

    public User toEntity() {
        UserId userId = UserId.create(id);
        Nickname nickname = Nickname.create(this.nickname);
        UserEmail email = UserEmail.create(this.email);
        Role role = Role.from(this.role);
        Oauth2Provider oauth2Provider = Oauth2Provider.valueOf(this.oauth2Provider);
        Oauth2Info oauth2Info = Oauth2Info.create(oauth2Id, oauth2Provider);
        AuthenticationCode code = AuthenticationCode.create(this.authenticationCode);
        return User.withId(userId, nickname, oauth2Info, profileImageUrl, role, email, code, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
    }

}
