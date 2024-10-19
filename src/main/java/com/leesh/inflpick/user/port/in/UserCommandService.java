package com.leesh.inflpick.user.port.in;

import com.leesh.inflpick.user.v2.core.entity.AuthenticationProcess;
import com.leesh.inflpick.user.v2.core.entity.User;

public interface UserCommandService {

    String create(UserCommand command);

    AuthenticationProcess authenticate(User user);
}
