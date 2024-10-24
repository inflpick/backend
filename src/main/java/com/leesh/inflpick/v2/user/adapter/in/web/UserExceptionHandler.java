package com.leesh.inflpick.v2.user.adapter.in.web;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorResponse;
import com.leesh.inflpick.v2.user.application.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.shared.adapter.in.web.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        log.error("UserNotFoundException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, UserApiErrorCode.USER_NOT_FOUND);
    }

}
