package com.leesh.inflpick.common.adapter.in.web.filter.security;

import com.leesh.inflpick.common.application.port.out.uuid.UuidPort;
import com.leesh.inflpick.token.adapter.out.token.jwt.vo.AuthProperties;
import com.leesh.inflpick.token.domain.vo.GrantType;
import com.leesh.inflpick.user.application.port.out.CommandUserPort;
import com.leesh.inflpick.user.domain.User;
import com.leesh.inflpick.user.domain.vo.AuthenticationCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthProperties authProperties;
    private final UuidPort uuidPort;
    private final CommandUserPort commandUserPort;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof CustomOauth2User)) {
            throw new UnauthorizedException("로그인에 실패하였습니다.");
        }

        User user = ((CustomOauth2User) principal).user();
        String uuid = uuidPort.uuid();
        AuthenticationCode authenticationCode = AuthenticationCode.create(uuid);
        User authenticationStartedUser = user.startAuthentication(authenticationCode);
        commandUserPort.save(authenticationStartedUser);
        String redirectUri = authProperties.redirectUri() + "?" + GrantType.AUTHENTICATION_CODE.toLowerCaseName() + "=" + authenticationCode.code();
        response.addHeader(HttpHeaders.LOCATION, redirectUri);
        response.sendRedirect(redirectUri);
    }
}
