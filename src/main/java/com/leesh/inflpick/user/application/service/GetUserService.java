package com.leesh.inflpick.user.application.service;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.user.application.dto.GetUserResponse;
import com.leesh.inflpick.user.application.exception.UserNotFoundException;
import com.leesh.inflpick.user.application.port.in.GetUserUseCase;
import com.leesh.inflpick.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.user.domain.User;
import com.leesh.inflpick.user.domain.vo.UserId;
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
    public PageResponse<GetUserResponse> getPage(PageRequest request) {
        PageResponse<User> userPage = queryUserPort.query(request);
        List<GetUserResponse> getUserResponse = userPage.contents().stream()
                .map(GetUserResponse::create)
                .toList();
        return PageResponse.create(getUserResponse,
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
