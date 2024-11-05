package com.leesh.inflpick.v2.user.application.service;

import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.user.application.dto.GetUserResponse;
import com.leesh.inflpick.v2.user.application.exception.UserNotFoundException;
import com.leesh.inflpick.v2.user.application.port.in.GetUserUseCase;
import com.leesh.inflpick.v2.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetUserService implements GetUserUseCase {

    private final QueryUserPort queryUserPort;

    @Override
    public OffsetPageResponse<GetUserResponse> getPage(OffsetPageRequest request) {
        OffsetPageResponse<User> userPage = queryUserPort.query(request);
        List<GetUserResponse> getUserResponse = userPage.contents().stream()
                .map(GetUserResponse::create)
                .toList();
        return OffsetPageResponse.create(getUserResponse,
                userPage.currentPage(),
                userPage.totalPages(),
                userPage.size(),
                userPage.totalElements(),
                userPage.sortProperties());
    }

    @Override
    public GetUserResponse get(UserId id) {
        User user = queryUserPort.query(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return GetUserResponse.create(user);
    }
}
