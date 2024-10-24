package com.leesh.inflpick.v2.token.application.service;

import com.leesh.inflpick.v2.token.application.dto.TokenResponse;
import com.leesh.inflpick.v2.token.application.dto.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.token.application.port.in.CreateTokenUseCase;
import com.leesh.inflpick.v2.token.application.port.in.RefreshTokenUseCase;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredRefreshTokenException;
import com.leesh.inflpick.v2.token.application.port.in.exception.InvalidTokenException;
import com.leesh.inflpick.v2.token.adapter.out.TokenExtractor;
import com.leesh.inflpick.v2.token.adapter.out.TokenGenerator;
import com.leesh.inflpick.v2.token.adapter.out.TokenValidator;
import com.leesh.inflpick.v2.user.application.port.out.CommandUserPort;
import com.leesh.inflpick.v2.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.AuthenticationCode;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateTokenService implements CreateTokenUseCase, RefreshTokenUseCase {

    private final TokenGenerator tokenGenerator;
    private final QueryUserPort queryUserPort;
    private final CommandUserPort commandUserPort;
    private final TokenValidator tokenValidator;
    private final TokenExtractor tokenExtractor;

    @Override
    public TokenResponse create(AuthenticationCodeTokenRequest request) {
        AuthenticationCode code = AuthenticationCode.create(request.getCode());
        User user = queryUserPort.query(code)
                .orElseThrow(() -> new ExpiredAuthenticationCodeException("Expired authentication code"));
        Token accessToken = tokenGenerator.generate(user.getId(), TokenType.ACCESS);
        Token refreshToken = tokenGenerator.generate(user.getId(), TokenType.REFRESH);
        user.completeAuthentication();
        commandUserPort.save(user);
        return TokenResponse.create(accessToken, refreshToken);
    }

    @Override
    public TokenResponse refresh(Token refreshToken) {
        validateExpiredToken(refreshToken);
        if (tokenValidator.verify(refreshToken, TokenType.REFRESH)) {
            UserId userId = tokenExtractor.extract(refreshToken);
            Token accessToken = tokenGenerator.generate(userId, TokenType.ACCESS);
            Token newRefreshToken = tokenGenerator.generate(userId, TokenType.REFRESH);
            return TokenResponse.create(accessToken, newRefreshToken);
        } else {
            throw new InvalidTokenException("Invalid refresh token");
        }
    }

    private void validateExpiredToken(Token refreshToken) {
        if (tokenValidator.isExpired(refreshToken)) {
            throw new ExpiredRefreshTokenException("Expired refresh token");
        }
    }
}
