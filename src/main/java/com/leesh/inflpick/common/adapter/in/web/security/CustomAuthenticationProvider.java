package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.out.jwt.Jwt;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.port.out.Token;
import com.leesh.inflpick.user.port.out.TokenService;
import com.leesh.inflpick.user.port.out.TokenType;
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

        String jwtString = (String) authentication.getPrincipal();
        Token token = new Jwt(jwtString, 0);
        if (tokenService.verifyToken(token, TokenType.ACCESS)) {
            User user = tokenService.extractToken(token);
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
