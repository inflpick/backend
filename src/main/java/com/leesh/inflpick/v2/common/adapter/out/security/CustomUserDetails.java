package com.leesh.inflpick.v2.common.adapter.out.security;

import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record CustomUserDetails(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = user.role();
        GrantedAuthority grantedAuthority = role::name;
        return List.of(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return "";
    }
    @Override
    public String getUsername() {
        return user.id().id();
    }
}
