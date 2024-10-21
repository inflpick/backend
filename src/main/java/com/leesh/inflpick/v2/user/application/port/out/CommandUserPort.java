package com.leesh.inflpick.v2.user.application.port.out;

import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface CommandUserPort {

    UserId save(User user);

}
