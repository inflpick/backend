package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.common.adapter.in.web.exception.UnauthorizedException;
import com.leesh.inflpick.user.adapter.out.jwt.CookieProperties;
import com.leesh.inflpick.user.v2.core.entity.AuthenticationProcess;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.AuthenticationCode;
import com.leesh.inflpick.user.port.in.UserCommandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final CookieProperties cookieProperties;
    private final UserCommandService userCommandService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof CustomOauth2User)) {
            throw new UnauthorizedException("로그인에 실패하였습니다.");
        }

        User user = ((CustomOauth2User) principal).user();
        AuthenticationProcess authenticationProcess = userCommandService.authenticate(user);
        Boolean secure = cookieProperties.secure();
        String redirectUri = cookieProperties.redirectUri();
        String domain = cookieProperties.domain();
        String sameSite = cookieProperties.sameSite();
        String path = cookieProperties.path();

        AuthenticationCode code = authenticationProcess.getCode();
        ResponseCookie authenticationCodeCookie = ResponseCookie.from("authentication_code", code.value())
                .httpOnly(false)
                .domain(domain)
                .sameSite(sameSite)
                .secure(secure)
                .path(path)
                .maxAge(60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, authenticationCodeCookie.toString());
        response.addHeader(HttpHeaders.LOCATION, redirectUri);
        response.sendRedirect(redirectUri);
    }
}
