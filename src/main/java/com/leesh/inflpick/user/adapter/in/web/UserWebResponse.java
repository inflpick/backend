package com.leesh.inflpick.user.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.user.core.domain.User;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserWebResponse(
        String id,
        String nickname,
        String profileImageUrl,
        String email,
        String role,
        String oauth2Type,
        String oauth2Id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant joinedDate) implements UserWebResponseApiDocs {

    public static UserWebResponse from(User user) {
        return UserWebResponse.builder()
                .id(user.getId())
                .role(user.getRole().name())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .email(user.getEmail())
                .oauth2Type(user.getOauth2Type())
                .oauth2Id(user.getOauth2UserId())
                .joinedDate(user.getCreatedDate())
                .build();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getOauth2Type() {
        return oauth2Type;
    }

    @Override
    public String getOauth2Id() {
        return oauth2Id;
    }

    @Override
    public Instant getJoinedDate() {
        return joinedDate;
    }
}
