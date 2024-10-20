package com.leesh.inflpick.auth.v2.adapter.security;

import com.leesh.inflpick.user.v2.core.entity.vo.Role;
import com.leesh.inflpick.user.v2.core.entity.User;
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
        return user.getId().value();
    }
}
