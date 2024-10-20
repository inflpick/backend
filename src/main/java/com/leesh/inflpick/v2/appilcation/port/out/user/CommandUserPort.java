package com.leesh.inflpick.v2.appilcation.port.out.user;

import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface CommandUserPort {

    UserId save(User user);

}
