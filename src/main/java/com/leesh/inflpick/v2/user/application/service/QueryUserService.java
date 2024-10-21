package com.leesh.inflpick.v2.user.application.service;

import com.leesh.inflpick.v2.shared.application.dto.OffsetPage;
import com.leesh.inflpick.v2.shared.application.dto.OffsetPageQuery;
import com.leesh.inflpick.v2.user.application.port.in.QueryUserUseCase;
import com.leesh.inflpick.v2.user.application.port.in.exception.UserNotFoundException;
import com.leesh.inflpick.v2.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.AuthenticationCode;
import com.leesh.inflpick.v2.user.domain.vo.Oauth2Info;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
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
