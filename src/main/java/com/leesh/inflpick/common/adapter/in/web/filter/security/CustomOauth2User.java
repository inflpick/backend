package com.leesh.inflpick.common.adapter.in.web.filter.security;

import com.leesh.inflpick.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

record CustomOauth2User(User user) implements OAuth2User {

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of(
                "id", user.id(),
                "nickname", user.nickname(),
                "profile_image_url", user.profileImageUrl(),
                "email", "");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return user.id().id();
    }
}
