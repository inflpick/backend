package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.common.adapter.in.web.exception.UnauthorizedException;
import com.leesh.inflpick.user.adapter.out.jwt.CookieProperties;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.out.Token;
import com.leesh.inflpick.user.port.out.TokenService;
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

    private final TokenService tokenService;
    private final CookieProperties cookieProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof CustomOauth2User)) {
            throw new UnauthorizedException("로그인에 실패하였습니다.");
        }

        User user = ((CustomOauth2User) principal).user();
        Token accessToken = tokenService.createAccessToken(user);
        Token refreshToken = tokenService.createRefreshToken(user);

        Boolean secure = cookieProperties.secure();
        String redirectUri = cookieProperties.redirectUri();
        String domain = cookieProperties.domain();
        String sameSite = cookieProperties.sameSite();
        String path = cookieProperties.path();

        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", accessToken.value())
                .httpOnly(false)
                .domain(domain)
                .sameSite(sameSite)
                .secure(secure)
                .path(path)
                .maxAge(accessToken.expiresInSeconds())
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken.value())
                .httpOnly(true)
                .domain(domain)
                .sameSite(sameSite)
                .secure(secure)
                .path(path)
                .maxAge(refreshToken.expiresInSeconds())
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        response.addHeader(HttpHeaders.LOCATION, redirectUri);
        response.sendRedirect(redirectUri);
    }
}
