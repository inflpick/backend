package com.leesh.inflpick.v2.appilcation.service.user;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.v2.domain.user.AuthenticationProcess;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.appilcation.port.out.user.dto.SocialUserRequest;
import com.leesh.inflpick.v2.appilcation.port.in.user.AuthenticateUserUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.CreateSocialUserUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.GetUserPageUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.GetUserUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.exception.DuplicatedSocialUserException;
import com.leesh.inflpick.v2.appilcation.port.out.user.CommandUserPort;
import com.leesh.inflpick.v2.appilcation.port.out.user.QueryUserPort;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Builder
@Service
public class UserService implements CreateSocialUserUseCase, GetUserPageUseCase, GetUserUseCase, AuthenticateUserUseCase {

    private final QueryUserPort queryUserPort;
    private final CommandUserPort commandUserPort;
    private final UuidHolder uuidHolder;

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

    @Override
    public User getUser(UserId userId) {
        return queryUserPort.query(userId)
                .orElseThrow();
    }

    @Override
    public User getUser(AuthenticationCode authenticationCode) {
        return queryUserPort.query(authenticationCode)
                .orElseThrow();
    }

    @Override
    public AuthenticationProcess authenticate(UserId userId) {
        User user = getUser(userId);
        String uuid = uuidHolder.uuid();
        AuthenticationCode authenticationCode = AuthenticationCode.create(uuid);
        AuthenticationProcess authenticationProcess = user.startAuthentication(authenticationCode);
        commandUserPort.save(user);
        return authenticationProcess;
    }
}
