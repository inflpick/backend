package com.leesh.inflpick.v2.appilcation.dto.token;

import com.leesh.inflpick.v2.domain.token.vo.Token;

public record TokenResponse(Token accessToken, Token refreshToken) {
}
