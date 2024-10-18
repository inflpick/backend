package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.out.jwt.Jwt;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.out.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String jwt = (String) authentication.getPrincipal();
        Jwt jwtAuthentication = new Jwt(jwt, 0);
        if (tokenService.verifyToken(jwtAuthentication)) {
            User user = tokenService.extractToken(jwtAuthentication);
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return CustomAuthenticationToken.withAuthenticated(userDetails, "",
                    List.of((GrantedAuthority) () -> user.getRole().name()));
        }

        return CustomAuthenticationToken.withoutAuthenticated(null, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
