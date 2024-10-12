package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String id) {
        super(id, "유저");
    }
}
