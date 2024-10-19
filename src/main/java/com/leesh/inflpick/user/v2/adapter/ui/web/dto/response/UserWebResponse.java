package com.leesh.inflpick.user.v2.adapter.ui.web.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.user.v2.adapter.docs.swagger.UserWebResponseDocs;
import com.leesh.inflpick.user.v2.core.entity.User;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserWebResponse(
        String id,
        String nickname,
        String profileImageUrl,
        String email,
        String role,
        String oauth2Provider,
        String oauth2Id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant joinedDate) implements UserWebResponseDocs {

    public static UserWebResponse from(User user) {
        return UserWebResponse.builder()
                .id(user.getId().value())
                .role(user.getRole().name())
                .nickname(user.getNickname().value())
                .profileImageUrl(user.getProfileImageUrl())
                .email(user.getEmail().value())
                .oauth2Provider(user.getOauth2Provider().name())
                .oauth2Id(user.getOauth2Id())
                .joinedDate(user.getCreatedDate())
                .build();
    }
}