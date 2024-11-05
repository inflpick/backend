package com.leesh.inflpick.v2.influencer.adapter.in.web.controller.exception;

import com.leesh.inflpick.v2.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.influencer.adapter.in.web.controller.constant.CommonInfluencerApiErrorCode;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.common.adapter.in.web.exception.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@Order(0)
@RestControllerAdvice(basePackages = "com.leesh.inflpick.v2.influencer.adapter.in.web.controller")
public class CommonInfluencerExceptionHandler {

    @ExceptionHandler(InfluencerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleInfluencerNotFoundException(InfluencerNotFoundException e, HttpServletRequest request) {
        log.error("InfluencerNotFoundException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonInfluencerApiErrorCode.NOT_FOUND);
    }
}
