package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.core.domain.Role;
import com.leesh.inflpick.user.core.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record CustomUserDetails(User user) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = user.getRole();
        GrantedAuthority grantedAuthority = role::name;
        return List.of(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return "";
    }
    @Override
    public String getUsername() {
        return user.getId();
    }
}
