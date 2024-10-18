package com.leesh.inflpick.user.port.in;

import com.leesh.inflpick.user.core.domain.AuthenticationProcess;
import com.leesh.inflpick.user.core.domain.User;

public interface UserCommandService {

    String create(UserCommand command);

    AuthenticationProcess authenticate(User user);
}
