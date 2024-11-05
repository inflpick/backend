package com.leesh.inflpick.v2.keyword.adapter.in.web.controller.exception;

import com.leesh.inflpick.v2.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.keyword.adapter.in.web.controller.constant.CommonKeywordApiErrorCode;
import com.leesh.inflpick.v2.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.v2.keyword.application.exception.KeywordNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.common.adapter.in.web.exception.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@Order(0)
@RestControllerAdvice(basePackages = "com.leesh.inflpick.v2.keyword.adapter.in.web.controller")
public class CommonKeywordExceptionHandler {

    @ExceptionHandler(AlreadyExistKeywordNameException.class)
    public ResponseEntity<ApiErrorResponse> handleAlreadyExistKeywordNameException(AlreadyExistKeywordNameException e, HttpServletRequest request) {
        log.error("AlreadyExistKeywordNameException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonKeywordApiErrorCode.ALREADY_EXIST_KEYWORD);
    }

    @ExceptionHandler(KeywordNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundKeywordException(KeywordNotFoundException e, HttpServletRequest request) {
        log.error("NotFoundKeywordException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonKeywordApiErrorCode.NOT_FOUND_KEYWORD);
    }
}
