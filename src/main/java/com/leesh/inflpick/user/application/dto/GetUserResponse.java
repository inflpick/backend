package com.leesh.inflpick.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.user.adapter.out.docs.swagger.UserResponseDocs;
import com.leesh.inflpick.user.domain.User;

import java.time.Instant;

public record GetUserResponse(String id,
                              String nickname,
                              String profileImageUrl,
                              String email,
                              String role,
                              String oauth2Provider,
                              String oauth2Id,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                           Instant joinedDate) implements UserResponseDocs {

    public static GetUserResponse create(User user) {
        return new GetUserResponse(
                user.id().id(),
                user.nickname().nickname(),
                user.profileImageUrl(),
                user.email().email(),
                user.role().name(),
                user.getOauth2Provider().name(),
                user.getOauth2Id(),
                user.createdDate()
        );
    }
}