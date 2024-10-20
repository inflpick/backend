package com.leesh.inflpick.v2.appilcation.port.in.user;

import com.leesh.inflpick.v2.domain.user.dto.UserCommand;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface CommandUserUseCase {

    UserId create(UserCommand userCommand);

}
