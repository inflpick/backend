package com.leesh.inflpick.common.adapter.in.web.filter.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leesh.inflpick.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.common.domain.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        Object exception = request.getAttribute("exception");
        ErrorCode errorCode;
        if (exception instanceof ExpiredAuthenticationException) {
            errorCode = (ExpiredAuthenticationException) exception;
        } else if (exception instanceof InvalidAuthenticationException) {
            errorCode = (InvalidAuthenticationException) exception;
        } else {
            errorCode = new InvalidAuthenticationException();
        }

        ApiErrorResponse responseBody = ApiErrorResponse.create(request, errorCode);
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        response.getWriter().flush();
    }
}
