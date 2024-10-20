package com.leesh.inflpick.v2.domain.user.dto;

import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.Nickname;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.Role;
import com.leesh.inflpick.v2.domain.user.vo.UserEmail;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder(builderMethodName = "requiredBuilder")
public final class UserCommand {

    private final Nickname nickname;
    private final Oauth2Info oauth2Info;
    @Builder.Default
    private final UserEmail email = UserEmail.empty();
    @Builder.Default
    private final String profileImageUrl = "";
    @Builder.Default
    private final Role role = Role.USER;

    public static UserCommandBuilder builder(Nickname nickname, Oauth2Info oauth2Info) {
        return requiredBuilder()
                .nickname(nickname)
                .oauth2Info(oauth2Info);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserCommand) obj;
        return Objects.equals(this.nickname, that.nickname) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.profileImageUrl, that.profileImageUrl) &&
                Objects.equals(this.oauth2Info, that.oauth2Info) &&
                Objects.equals(this.role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, email, profileImageUrl, oauth2Info, role);
    }

    public User toEntity() {
        return User.builder(nickname, oauth2Info)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .role(role)
                .build();
    }

}
