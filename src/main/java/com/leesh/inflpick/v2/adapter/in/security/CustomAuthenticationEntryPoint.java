package com.leesh.inflpick.v2.adapter.in.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leesh.inflpick.v2.adapter.in.web.common.ExceptionController;
import com.leesh.inflpick.v2.adapter.in.web.common.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.adapter.in.web.common.error.CommonApiErrorCode;
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
        CommonApiErrorCode apiErrorCode;
        if (exception instanceof ExpiredAuthenticationException) {
            apiErrorCode = CommonApiErrorCode.EXPIRED_TOKEN;
        } else if (exception instanceof InvalidAuthenticationException) {
            apiErrorCode = CommonApiErrorCode.INVALID_TOKEN;
        } else {
            apiErrorCode = CommonApiErrorCode.UNAUTHORIZED;
        }

        ApiErrorResponse responseBody = ExceptionController.createResponseEntityFromApiErrorCode(request, apiErrorCode).getBody();
        response.setStatus(apiErrorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        response.getWriter().flush();
    }
}
