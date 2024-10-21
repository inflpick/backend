package com.leesh.inflpick.v2.appilcation.service.user;

import com.leesh.inflpick.v2.appilcation.dto.common.OffsetPage;
import com.leesh.inflpick.v2.appilcation.dto.common.OffsetPageQuery;
import com.leesh.inflpick.v2.appilcation.port.in.user.QueryUserUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.exception.UserNotFoundException;
import com.leesh.inflpick.v2.appilcation.port.out.user.QueryUserPort;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Builder
@Service
class QueryUserService implements QueryUserUseCase {

    private final QueryUserPort queryUserPort;

    @Override
    public User query(UserId userId) throws UserNotFoundException {
        return queryUserPort.query(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found, userId: " + userId));
    }

    @Override
    public User query(AuthenticationCode authenticationCode) throws UserNotFoundException {
        return queryUserPort.query(authenticationCode)
                .orElseThrow(() -> new UserNotFoundException("User not found, authenticationCode: " + authenticationCode));
    }

    @Override
    public User query(Oauth2Info oauth2Info) {
        return queryUserPort.query(oauth2Info)
                .orElseThrow(() -> new UserNotFoundException("User not found, oauth2Info: " + oauth2Info));
    }

    @Override
    public OffsetPage<User> query(OffsetPageQuery query) {
        return queryUserPort.query(query);
    }
}
