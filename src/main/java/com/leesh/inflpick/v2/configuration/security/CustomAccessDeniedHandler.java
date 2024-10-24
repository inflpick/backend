package com.leesh.inflpick.v2.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leesh.inflpick.v2.shared.adapter.in.web.CommonExceptionHandler;
import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorResponse;
import com.leesh.inflpick.v2.shared.adapter.in.web.CommonApiErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        CommonApiErrorCode apiErrorCode = CommonApiErrorCode.FORBIDDEN;
        ApiErrorResponse responseBody = CommonExceptionHandler.createResponseEntityFromApiErrorCode(request, apiErrorCode).getBody();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        response.getWriter().flush();
    }
}
