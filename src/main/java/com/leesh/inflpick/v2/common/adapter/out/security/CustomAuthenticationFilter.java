package com.leesh.inflpick.v2.common.adapter.out.security;

import com.leesh.inflpick.v2.token.adapter.out.token.jwt.vo.JwtProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String jwt = authorization.substring("Bearer ".length());
            Authentication withoutAuthenticated = CustomAuthenticationToken.notAuthenticated(jwt, jwtProperties.secretKey());
            try {
                Authentication authenticate = authenticationManager.authenticate(withoutAuthenticated);
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            } catch (ExpiredAuthenticationException | InvalidAuthenticationException e) {
                SecurityContextHolder.clearContext();
                request.setAttribute("exception", e);
            }
        }

        filterChain.doFilter(request, response);
    }
}
