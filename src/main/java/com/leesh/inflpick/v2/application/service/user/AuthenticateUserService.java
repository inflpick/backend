package com.leesh.inflpick.v2.application.service.user;

import com.leesh.inflpick.v2.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.v2.application.port.in.user.AuthenticateUserUseCase;
import com.leesh.inflpick.v2.application.port.in.user.exception.UserNotFoundException;
import com.leesh.inflpick.v2.application.port.out.user.CommandUserPort;
import com.leesh.inflpick.v2.application.port.out.user.QueryUserPort;
import com.leesh.inflpick.v2.domain.user.AuthenticationProcess;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
class AuthenticateUserService implements AuthenticateUserUseCase {

    private final CommandUserPort commandUserPort;
    private final QueryUserPort queryUserPort;
    private final UuidHolder uuidHolder;

    @Override
    public AuthenticationProcess authenticate(UserId userId) {
        User user = queryUserPort.query(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        String uuid = uuidHolder.uuid();
        AuthenticationCode authenticationCode = AuthenticationCode.create(uuid);
        AuthenticationProcess process = user.startAuthentication(authenticationCode);
        commandUserPort.save(user);
        return process;
    }
}
