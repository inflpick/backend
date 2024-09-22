package com.leesh.inflpick.product.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.product.adapter.in.web.value.ProductCreateApiErrorCode;
import com.leesh.inflpick.product.core.exception.InvalidOnlineStoreException;
import com.leesh.inflpick.product.core.exception.ProductDescriptionValidationFailedException;
import com.leesh.inflpick.product.core.exception.ProductNameValidationFailedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.common.adapter.in.web.CommonExceptionControllerAdvice.createResponseEntityFromApiErrorCode;

@Slf4j
@Order(PriorityOrdered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackageClasses = ProductController.class)
public class ProductExceptionControllerAdvice {

    @ExceptionHandler(ProductNameValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerProductNameValidationFailedException(ProductNameValidationFailedException e, HttpServletRequest request) {
        log.warn("ProductNameValidationFailedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, ProductCreateApiErrorCode.PRODUCT_NAME_VALIDATION_FAILED);
    }

    @ExceptionHandler(ProductDescriptionValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerProductDescriptionValidationFailedException(ProductDescriptionValidationFailedException e, HttpServletRequest request) {
        log.warn("ProductDescriptionValidationFailedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, ProductCreateApiErrorCode.PRODUCT_DESCRIPTION_VALIDATION_FAILED);
    }

    @ExceptionHandler(InvalidOnlineStoreException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidOnlineStoreException(InvalidOnlineStoreException e, HttpServletRequest request) {
        log.warn("InvalidOnlineStoreException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, ProductCreateApiErrorCode.INVALID_ONLINE_STORE_TYPE);
    }

}
