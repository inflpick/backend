package com.leesh.inflpick.v2.configuration.security;

import com.leesh.inflpick.v2.token.adapter.out.token.jwt.JwtProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String jwt = authorization.substring("Bearer ".length());
            Authentication withoutAuthenticated = CustomAuthenticationToken.withoutAuthenticated(jwt, jwtProperties.secretKey());
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
