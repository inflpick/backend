package com.leesh.inflpick.user.port.in;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.Nickname;
import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Info;
import com.leesh.inflpick.user.v2.core.entity.vo.Role;
import com.leesh.inflpick.user.v2.core.entity.vo.UserEmail;
import lombok.Builder;

@Builder
public record UserCommand(
        Nickname nickname,
        UserEmail email,
        String profileImageUrl,
        Role role,
        Oauth2Info oauth2Info
) {

    public User toEntity(UuidHolder uuidHolder) {
        return User.builder(nickname, oauth2Info)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .role(role)
                .build();
    }

}
