package com.leesh.inflpick.v2.adapter.in.security;

import com.leesh.inflpick.common.adapter.in.web.exception.UnauthorizedException;
import com.leesh.inflpick.v2.adapter.out.token.jwt.AuthProperties;
import com.leesh.inflpick.v2.appilcation.port.in.user.AuthenticateUserUseCase;
import com.leesh.inflpick.v2.domain.token.vo.GrantType;
import com.leesh.inflpick.v2.domain.user.AuthenticationProcess;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import jakarta.servlet.ServletException;
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
    private final AuthenticateUserUseCase authenticateUserUseCase;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof CustomOauth2User)) {
            throw new UnauthorizedException("로그인에 실패하였습니다.");
        }

        User user = ((CustomOauth2User) principal).user();
        AuthenticationProcess authenticationProcess = authenticateUserUseCase.authenticate(user.getId());
        AuthenticationCode code = authenticationProcess.getCode();
        String redirectUri = authProperties.redirectUri() + "?" + GrantType.AUTHENTICATION_CODE.name() + "=" + code.value();
        response.addHeader(HttpHeaders.LOCATION, redirectUri);
        response.sendRedirect(redirectUri);
    }
}
