package com.leesh.inflpick.token.application.port.out;

import com.leesh.inflpick.token.domain.Token;
import com.leesh.inflpick.user.domain.vo.UserId;

public interface TokenExtractorPort {

    UserId extract(Token token);
}
