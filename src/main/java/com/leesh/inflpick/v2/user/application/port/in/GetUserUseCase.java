package com.leesh.inflpick.v2.user.application.port.in;

import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.user.application.dto.GetUserResponse;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface GetUserUseCase {

    OffsetPageResponse<GetUserResponse> getPage(OffsetPageRequest request);

    GetUserResponse get(UserId id);
}
