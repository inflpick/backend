package com.leesh.inflpick.v2.product.adapter.in.web.controller;

import com.leesh.inflpick.v2.product.adapter.in.web.exception.NotSupportOnlineStorePlatformException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(NotSupportOnlineStorePlatformException.class)
    public void handleNotSupportOnlineStorePlatformException(NotSupportOnlineStorePlatformException e) {
        log.error("NotSupportOnlineStorePlatformException occurred: {}", e.getMessage());
    }

}
