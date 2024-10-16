package com.leesh.inflpick.user.port.in;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.user.core.domain.*;
import lombok.Builder;

@Builder
public record UserCommand(
        Nickname nickname,
        UserEmail email,
        String profileImageUrl,
        Role role,
        Oauth2UserInfo oauth2UserInfo
) {

    public User toEntity(UuidHolder uuidHolder) {
        return User.builder()
                .id(uuidHolder.uuid())
                .email(email)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .role(role)
                .oauth2UserInfo(oauth2UserInfo)
                .build();
    }

}
