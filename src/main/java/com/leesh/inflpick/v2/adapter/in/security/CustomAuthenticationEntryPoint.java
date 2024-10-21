package com.leesh.inflpick.v2.adapter.in.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leesh.inflpick.v2.adapter.in.web.common.ExceptionController;
import com.leesh.inflpick.v2.adapter.in.web.common.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.adapter.in.web.common.error.CommonApiErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
        CommonApiErrorCode apiErrorCode = CommonApiErrorCode.UNAUTHORIZED;
        ApiErrorResponse responseBody = ExceptionController.createResponseEntityFromApiErrorCode(request, apiErrorCode).getBody();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        response.getWriter().flush();
    }
}
