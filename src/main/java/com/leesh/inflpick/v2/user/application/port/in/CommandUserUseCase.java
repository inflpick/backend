package com.leesh.inflpick.v2.user.application.port.in;

import com.leesh.inflpick.v2.user.application.dto.UserCommand;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface CommandUserUseCase {

    UserId create(UserCommand userCommand);

}
