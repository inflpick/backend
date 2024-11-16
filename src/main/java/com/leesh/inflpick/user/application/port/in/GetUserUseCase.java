package com.leesh.inflpick.user.application.port.in;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.user.application.dto.GetUserResponse;
import com.leesh.inflpick.user.domain.vo.UserId;

public interface GetUserUseCase {

    PageResponse<GetUserResponse> getPage(PageRequest request);

    GetUserResponse get(UserId id);
}
