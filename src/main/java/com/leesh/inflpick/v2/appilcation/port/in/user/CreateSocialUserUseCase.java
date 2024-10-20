package com.leesh.inflpick.v2.appilcation.port.in.user;

import com.leesh.inflpick.v2.domain.user.vo.UserId;
import com.leesh.inflpick.v2.appilcation.port.out.user.dto.SocialUserRequest;
import com.leesh.inflpick.v2.appilcation.port.in.user.exception.DuplicatedSocialUserException;

public interface CreateSocialUserUseCase {

    UserId create(SocialUserRequest request) throws DuplicatedSocialUserException;

}
