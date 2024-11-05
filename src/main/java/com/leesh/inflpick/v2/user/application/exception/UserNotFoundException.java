package com.leesh.inflpick.v2.user.application.exception;

import com.leesh.inflpick.v2.user.domain.vo.UserId;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UserId id) {
        super("User not found: " + id);
    }
}
