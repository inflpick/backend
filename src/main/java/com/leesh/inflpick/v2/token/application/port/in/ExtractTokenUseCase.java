package com.leesh.inflpick.v2.token.application.port.in;

import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.user.domain.vo.UserId;

public interface ExtractTokenUseCase {

    UserId extract(Token token);

}
