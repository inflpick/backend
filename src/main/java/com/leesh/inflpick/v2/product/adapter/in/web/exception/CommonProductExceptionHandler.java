package com.leesh.inflpick.v2.product.adapter.in.web.exception;

import com.leesh.inflpick.v2.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.product.adapter.in.web.constant.CommonProductApiErrorCode;
import com.leesh.inflpick.v2.product.application.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.common.adapter.in.web.exception.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@Order(0)
@RestControllerAdvice(basePackages = "com.leesh.inflpick.v2.product.adapter.in.web")
public class CommonProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleProductNotFoundException(ProductNotFoundException e, HttpServletRequest request) {
        log.error("ProductNotFoundException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonProductApiErrorCode.NOT_FOUND);
    }
}
