package com.leesh.inflpick.v2.configuration.security;

import com.leesh.inflpick.v2.token.adapter.out.token.jwt.Jwt;
import com.leesh.inflpick.v2.token.application.port.in.ExtractTokenUseCase;
import com.leesh.inflpick.v2.token.application.port.in.ValidateTokenUseCase;
import com.leesh.inflpick.v2.user.application.port.in.QueryUserUseCase;
import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;
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

    private final ValidateTokenUseCase validateTokenUseCase;
    private final ExtractTokenUseCase tokenUseCase;
    private final QueryUserUseCase queryUserUseCase;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String accessTokenString = (String) authentication.getPrincipal();
        Token accessToken = Jwt.create(accessTokenString);
        if (validateTokenUseCase.isExpired(accessToken)) {
            throw new ExpiredAuthenticationException("AccessToken is expired");
        }

        if (validateTokenUseCase.isValid(accessToken, TokenType.ACCESS)) {
            UserId userId = tokenUseCase.extract(accessToken);
            User user = queryUserUseCase.query(userId);
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return CustomAuthenticationToken.withAuthenticated(userDetails, "",
                    List.of((GrantedAuthority) () -> user.getRole().name()));
        } else {
            throw new InvalidAuthenticationException("Invalid accessToken");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
