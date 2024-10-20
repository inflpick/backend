package com.leesh.inflpick.v2.appilcation.port.in.user;

import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.domain.user.User;

public interface GetUserPageUseCase {

    OffsetPageResponse<User> getPage(OffsetPageRequest request);

}
