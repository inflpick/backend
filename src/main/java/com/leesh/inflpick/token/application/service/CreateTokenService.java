package com.leesh.inflpick.token.application.service;

import com.leesh.inflpick.token.application.dto.CreateTokenResponse;
import com.leesh.inflpick.token.application.port.in.CreateTokenUseCase;
import com.leesh.inflpick.token.application.port.in.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.token.application.port.in.exception.ExpiredRefreshTokenException;
import com.leesh.inflpick.token.application.port.in.exception.InvalidRefreshTokenException;
import com.leesh.inflpick.token.application.port.out.TokenExtractorPort;
import com.leesh.inflpick.token.application.port.out.TokenGeneratorPort;
import com.leesh.inflpick.token.application.port.out.TokenValidatorPort;
import com.leesh.inflpick.token.domain.Token;
import com.leesh.inflpick.token.domain.vo.TokenType;
import com.leesh.inflpick.user.application.port.out.CommandUserPort;
import com.leesh.inflpick.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.user.domain.User;
import com.leesh.inflpick.user.domain.vo.AuthenticationCode;
import com.leesh.inflpick.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateTokenService implements CreateTokenUseCase {

    private final TokenGeneratorPort tokenGeneratorPort;
    private final QueryUserPort queryUserPort;
    private final CommandUserPort commandUserPort;
    private final TokenValidatorPort tokenValidatorPort;
    private final TokenExtractorPort tokenExtractorPort;

    @Override
    public CreateTokenResponse create(AuthenticationCode code) throws ExpiredAuthenticationCodeException {
        User user = queryUserPort.query(code)
                .orElseThrow(ExpiredAuthenticationCodeException::new);
        Token accessToken = tokenGeneratorPort.generate(user.id(), TokenType.ACCESS);
        Token refreshToken = tokenGeneratorPort.generate(user.id(), TokenType.REFRESH);
        User endAuthenticateUser = user.endAuthenticate();
        commandUserPort.save(endAuthenticateUser);
        return CreateTokenResponse.create(accessToken, refreshToken);
    }

    @Override
    public CreateTokenResponse refresh(Token refreshToken) throws ExpiredRefreshTokenException, InvalidRefreshTokenException {
        validateExpiredToken(refreshToken);
        if (tokenValidatorPort.isValid(refreshToken, TokenType.REFRESH)) {
            UserId userId = tokenExtractorPort.extract(refreshToken);
            Token accessToken = tokenGeneratorPort.generate(userId, TokenType.ACCESS);
            Token newRefreshToken = tokenGeneratorPort.generate(userId, TokenType.REFRESH);
            return CreateTokenResponse.create(accessToken, newRefreshToken);
        } else {
            throw new InvalidRefreshTokenException(refreshToken.value());
        }
    }

    private void validateExpiredToken(Token refreshToken) {
        if (tokenValidatorPort.isExpired(refreshToken)) {
            throw new ExpiredRefreshTokenException();
        }
    }
}
