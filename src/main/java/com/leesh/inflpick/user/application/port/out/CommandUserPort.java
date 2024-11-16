package com.leesh.inflpick.user.application.port.out;

import com.leesh.inflpick.user.domain.User;
import com.leesh.inflpick.user.domain.vo.UserId;

public interface CommandUserPort {

    UserId save(User user);

}
