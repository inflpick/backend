package com.leesh.inflpick.v2.user.application.port.in;

import com.leesh.inflpick.v2.common.application.dto.PageRequest;
import com.leesh.inflpick.v2.common.application.dto.PageResponse;
import com.leesh.inflpick.v2.user.application.dto.GetUserResponse;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface GetUserUseCase {

    PageResponse<GetUserResponse> getPage(PageRequest request);

    GetUserResponse get(UserId id);
}
