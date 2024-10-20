package com.leesh.inflpick.v2.adapter.in.web.common.security;

import com.leesh.inflpick.v2.adapter.out.token.jwt.auth.Jwt;
import com.leesh.inflpick.v2.domain.auth.vo.Token;
import com.leesh.inflpick.v2.domain.auth.vo.TokenType;
import com.leesh.inflpick.v2.appilcation.port.in.auth.TokenUseCase;
import com.leesh.inflpick.v2.domain.user.User;
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

    private final TokenUseCase tokenUseCase;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String jwtString = (String) authentication.getPrincipal();
        Token token = new Jwt(jwtString, 0);
        if (tokenUseCase.verifyToken(token, TokenType.ACCESS)) {
            User user = tokenUseCase.extractToken(token);
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
