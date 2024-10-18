package com.leesh.inflpick.common.adapter.in.web.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.List;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final Object credentials;

    private CustomAuthenticationToken(Object principal, Object credentials) {
        super(List.of());
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(false);
    }

    private CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean implies(Subject subject) {
        return super.implies(subject);
    }

    public static Authentication withAuthenticated(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        return new CustomAuthenticationToken(principal, credentials, authorities);
    }

    public static Authentication withoutAuthenticated(Object principal, Object credentials) {
        return new CustomAuthenticationToken(principal, credentials);
    }
}
