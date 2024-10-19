package com.leesh.inflpick.user.v2.port.out;

import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.UserId;

public interface CommandUserPort {

    UserId save(User user);

}
