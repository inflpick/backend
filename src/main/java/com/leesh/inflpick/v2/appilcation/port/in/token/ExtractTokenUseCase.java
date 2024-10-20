package com.leesh.inflpick.v2.appilcation.port.in.token;

import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface ExtractTokenUseCase {

    UserId extract(Token token);

}
