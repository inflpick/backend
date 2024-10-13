package com.leesh.inflpick.user.port.in;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.user.core.domain.Nickname;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.Role;
import com.leesh.inflpick.user.core.domain.User;
import lombok.Builder;

@Builder
public record UserCommand(
        Nickname nickname,
        String profileImageUrl,
        String email,
        Role role,
        Oauth2UserInfo oauth2UserInfo
) {

    public User toEntity(UuidHolder uuidHolder) {
        return User.builder()
                .id(uuidHolder.uuid())
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .role(role)
                .oauth2UserInfo(oauth2UserInfo)
                .build();
    }

}
