package com.leesh.inflpick.v2.application.port.out.token;

import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.user.vo.UserId;

public interface TokenExtractor {

    UserId extract(Token token);
}
