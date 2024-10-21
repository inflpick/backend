package com.leesh.inflpick.v2.appilcation.service.user;

import com.leesh.inflpick.v2.appilcation.dto.user.UserCommand;
import com.leesh.inflpick.v2.appilcation.port.in.user.CommandUserUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.exception.DuplicatedSocialUserException;
import com.leesh.inflpick.v2.appilcation.port.out.user.CommandUserPort;
import com.leesh.inflpick.v2.appilcation.port.out.user.QueryUserPort;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
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
