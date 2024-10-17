package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.out.jwt.JwtAuthentication;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.out.AuthenticationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationTokenService authenticationTokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String jwt = (String) authentication.getPrincipal();
        JwtAuthentication jwtAuthentication = new JwtAuthentication(jwt, 0);
        if (authenticationTokenService.verifyToken(jwtAuthentication)) {
            User user = authenticationTokenService.extractToken(jwtAuthentication);
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return new JwtAuthenticationToken(userDetails, "",
                    List.of((GrantedAuthority) () -> user.getRole().name()));
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
