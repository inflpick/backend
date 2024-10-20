package com.leesh.inflpick.v2.adapter.in.security;

import com.leesh.inflpick.v2.adapter.out.token.jwt.Jwt;
import com.leesh.inflpick.v2.appilcation.port.in.token.ExtractTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.token.VerifyTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.QueryUserUseCase;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
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

    private final VerifyTokenUseCase verifyTokenUseCase;
    private final ExtractTokenUseCase tokenUseCase;
    private final QueryUserUseCase queryUserUseCase;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String jwtString = (String) authentication.getPrincipal();
        Token token = new Jwt(jwtString, 0);
        if (verifyTokenUseCase.verify(token, TokenType.ACCESS)) {
            UserId userId = tokenUseCase.extract(token);
            User user = queryUserUseCase.query(userId);
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
