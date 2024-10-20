package com.leesh.inflpick.v2.adapter.in.web.common.security;

import com.leesh.inflpick.v2.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public record CustomOauth2User(User user) implements OAuth2User {

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of(
                "id", user.getId(),
                "nickname", user.getNickname(),
                "profile_image_url", user.getProfileImageUrl(),
                "email", "");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return user.getId().value();
    }
}
