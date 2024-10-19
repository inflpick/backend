package com.leesh.inflpick.user.v2.port.in;

import com.leesh.inflpick.user.v2.core.dto.OffsetPageRequest;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageResponse;
import com.leesh.inflpick.user.v2.core.entity.User;

public interface GetUserPageUseCase {

    OffsetPageResponse<User> getPage(OffsetPageRequest request);

}
