package com.leesh.inflpick.v2.application.port.in.user;

import com.leesh.inflpick.v2.application.dto.user.UserCommand;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface CommandUserUseCase {

    UserId create(UserCommand userCommand);

}
