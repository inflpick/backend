package com.leesh.inflpick.v2.appilcation.service.token;

import com.leesh.inflpick.v2.appilcation.dto.user.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.appilcation.dto.user.TokenResponse;
import com.leesh.inflpick.v2.appilcation.port.in.token.CreateTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.exception.UserNotFoundException;
import com.leesh.inflpick.v2.appilcation.port.out.token.TokenGenerator;
import com.leesh.inflpick.v2.appilcation.port.out.user.CommandUserPort;
import com.leesh.inflpick.v2.appilcation.port.out.user.QueryUserPort;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateTokenService implements CreateTokenUseCase {

    private final TokenGenerator tokenGenerator;
    private final QueryUserPort queryUserPort;
    private final CommandUserPort commandUserPort;

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
}
