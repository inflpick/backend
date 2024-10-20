package com.leesh.inflpick.v2.adapter.in.web.common;

import com.leesh.inflpick.v2.appilcation.service.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundException(UserNotFoundException e) {
        log.error("userNotFoundException", e);
        return e.getMessage();
    }
}
