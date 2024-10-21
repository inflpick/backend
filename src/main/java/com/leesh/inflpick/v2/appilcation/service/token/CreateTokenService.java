package com.leesh.inflpick.v2.appilcation.service.token;

import com.leesh.inflpick.v2.appilcation.dto.token.TokenResponse;
import com.leesh.inflpick.v2.appilcation.dto.user.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.appilcation.port.in.token.CreateTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.token.RefreshTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.token.exception.ExpiredRefreshTokenException;
import com.leesh.inflpick.v2.appilcation.port.in.token.exception.InvalidTokenException;
import com.leesh.inflpick.v2.appilcation.port.in.user.exception.UserNotFoundException;
import com.leesh.inflpick.v2.appilcation.port.out.token.TokenExtractor;
import com.leesh.inflpick.v2.appilcation.port.out.token.TokenGenerator;
import com.leesh.inflpick.v2.appilcation.port.out.token.TokenValidator;
import com.leesh.inflpick.v2.appilcation.port.out.user.CommandUserPort;
import com.leesh.inflpick.v2.appilcation.port.out.user.QueryUserPort;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
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
                .orElseThrow(() -> new UserNotFoundException("User not found"));
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
            return TokenResponse.create(accessToken, refreshToken);
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
