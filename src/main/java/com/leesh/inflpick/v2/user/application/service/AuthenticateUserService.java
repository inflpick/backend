package com.leesh.inflpick.v2.user.application.service;

import com.leesh.inflpick.v2.shared.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.v2.user.application.port.in.AuthenticateUserUseCase;
import com.leesh.inflpick.v2.user.application.port.in.exception.UserNotFoundException;
import com.leesh.inflpick.v2.user.application.port.out.CommandUserPort;
import com.leesh.inflpick.v2.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.v2.user.domain.AuthenticationProcess;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.AuthenticationCode;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
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
