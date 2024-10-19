package com.leesh.inflpick.user.v2.port.out;

import com.leesh.inflpick.user.v2.core.dto.OffsetPageRequest;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Info;
import com.leesh.inflpick.user.v2.core.entity.vo.UserId;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageResponse;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageQuery;

import java.util.Optional;

public interface QueryUserPort {

    Optional<User> query(Oauth2Info oauth2Info);

    User query(UserId userId);

    OffsetPageResponse<User> query(OffsetPageRequest request);
}
