package com.leesh.inflpick.user.v2.core.application;

import com.leesh.inflpick.user.v2.core.dto.OffsetPageRequest;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageResponse;
import com.leesh.inflpick.user.v2.core.dto.SocialUserRequest;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Info;
import com.leesh.inflpick.user.v2.core.entity.vo.UserId;
import com.leesh.inflpick.user.v2.port.in.CreateSocialUserUseCase;
import com.leesh.inflpick.user.v2.port.in.GetUserPageUseCase;
import com.leesh.inflpick.user.v2.port.in.exception.DuplicatedSocialUserException;
import com.leesh.inflpick.user.v2.port.out.CommandUserPort;
import com.leesh.inflpick.user.v2.port.out.QueryUserPort;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Builder
@Service
public class UserService implements CreateSocialUserUseCase, GetUserPageUseCase {

    private final QueryUserPort queryUserPort;
    private final CommandUserPort commandUserPort;

    @Override
    public UserId create(SocialUserRequest request) throws DuplicatedSocialUserException {
        User user = request.toEntity();
        Oauth2Info oauth2Info = user.getOauth2Info();
        validateDuplicateUser(oauth2Info);
        return commandUserPort.save(user);
    }

    private void validateDuplicateUser(Oauth2Info oauth2Info) {
        queryUserPort.query(oauth2Info).ifPresent(u -> {
            throw new DuplicatedSocialUserException("Already registered social user, provider: %s, registered date: %s".formatted(oauth2Info.getProvider(), u.getCreatedDate()));
        });
    }

    @Override
    public OffsetPageResponse<User> getPage(OffsetPageRequest request) {
        return queryUserPort.query(request);
    }
}
