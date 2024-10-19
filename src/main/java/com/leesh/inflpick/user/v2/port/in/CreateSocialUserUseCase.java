package com.leesh.inflpick.user.v2.port.in;

import com.leesh.inflpick.user.v2.core.entity.vo.UserId;
import com.leesh.inflpick.user.v2.core.dto.SocialUserRequest;
import com.leesh.inflpick.user.v2.port.in.exception.DuplicatedSocialUserException;

public interface CreateSocialUserUseCase {

    UserId create(SocialUserRequest request) throws DuplicatedSocialUserException;

}
