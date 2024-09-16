package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.influencer.adapter.in.web.UserInputException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserInputException(UserInputException e, HttpServletRequest request) {
        log.warn("UserInputException: {}, cause: {}", e.getMessage(), ErrorCode.CO_0001.getCause());
        return new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                ErrorCode.CO_0001.name(),
                e.getMessage(),
                request.getMethod(),
                request.getRequestURI()
        );
    }

}
