package com.leesh.inflpick.v2.user.application.service;

import com.leesh.inflpick.v2.user.application.dto.UserCommand;
import com.leesh.inflpick.v2.user.application.port.in.CommandUserUseCase;
import com.leesh.inflpick.v2.user.application.port.in.exception.DuplicatedSocialUserException;
import com.leesh.inflpick.v2.user.application.port.out.CommandUserPort;
import com.leesh.inflpick.v2.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.Oauth2Info;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CommandUserService implements CommandUserUseCase {

    private final CommandUserPort commandUserPort;
    private final QueryUserPort queryUserPort;

    @Override
    public UserId create(UserCommand command) {
        User entity = command.toEntity();
        validateDuplicateUser(entity.getOauth2Info());
        return commandUserPort.save(entity);
    }

    private void validateDuplicateUser(Oauth2Info oauth2Info) {
        queryUserPort.query(oauth2Info).ifPresent(u -> {
            throw new DuplicatedSocialUserException("Already registered social user, provider: %s, registered date: %s".formatted(oauth2Info.getProvider(), u.getCreatedDate()));
        });
    }
}
