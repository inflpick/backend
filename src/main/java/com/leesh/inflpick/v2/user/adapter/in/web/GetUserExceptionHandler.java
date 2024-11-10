package com.leesh.inflpick.v2.user.adapter.in.web;

import com.leesh.inflpick.v2.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.user.adapter.in.web.constant.GetUserApiErrorCode;
import com.leesh.inflpick.v2.user.application.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.common.adapter.in.web.exception.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@Order(0)
@RestControllerAdvice(basePackageClasses = GetUserController.class)
public class GetUserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        log.error("UserNotFoundException", e);
        return createResponseEntityFromApiErrorCode(request, GetUserApiErrorCode.USER_NOT_FOUND);
    }

}
