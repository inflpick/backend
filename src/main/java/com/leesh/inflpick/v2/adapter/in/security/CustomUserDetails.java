package com.leesh.inflpick.v2.adapter.in.security;

import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

record CustomUserDetails(User user) implements UserDetails {

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
        return user.getId().getValue();
    }
}
