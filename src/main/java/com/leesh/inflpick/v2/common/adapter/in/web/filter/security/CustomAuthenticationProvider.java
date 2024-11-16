package com.leesh.inflpick.v2.common.adapter.in.web.filter.security;

import com.leesh.inflpick.v2.token.adapter.out.token.jwt.Jwt;
import com.leesh.inflpick.v2.token.application.port.out.TokenExtractorPort;
import com.leesh.inflpick.v2.token.application.port.out.TokenValidatorPort;
import com.leesh.inflpick.v2.token.domain.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;
import com.leesh.inflpick.v2.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class CustomAuthenticationProvider implements AuthenticationProvider {

    private final TokenValidatorPort tokenValidatorPort;
    private final TokenExtractorPort tokenExtractorPort;
    private final QueryUserPort queryUserPort;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String accessTokenString = (String) authentication.getPrincipal();
        Token accessToken = Jwt.create(accessTokenString);
        if (tokenValidatorPort.isExpired(accessToken)) {
            throw new ExpiredAuthenticationException();
        }

        if (tokenValidatorPort.isValid(accessToken, TokenType.ACCESS)) {
            UserId userId = tokenExtractorPort.extract(accessToken);
            User user = queryUserPort.query(userId).orElseThrow(InvalidAuthenticationException::new);
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return CustomAuthenticationToken.authenticated(userDetails,
                    "",
                    List.of((GrantedAuthority) () -> user.role().name()));
        } else {
            throw new InvalidAuthenticationException();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
