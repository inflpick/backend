package com.leesh.inflpick.v2.influencer.adapter.in.web.controller.exception;

import com.leesh.inflpick.v2.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.influencer.adapter.in.web.controller.CreateInfluencerController;
import com.leesh.inflpick.v2.influencer.adapter.in.web.controller.constant.CreateInfluencerApiErrorCode;
import com.leesh.inflpick.v2.influencer.domain.exception.NotSupportedSnsPlatformException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.common.adapter.in.web.exception.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@Order(0)
@RestControllerAdvice(basePackageClasses = CreateInfluencerController.class)
public class CreateInfluencerExceptionHandler {

    @ExceptionHandler(NotSupportedSnsPlatformException.class)
    public ResponseEntity<ApiErrorResponse> handleNotSupportedSnsPlatformException(NotSupportedSnsPlatformException e, HttpServletRequest request) {
        log.error("not supported sns platform: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CreateInfluencerApiErrorCode.NOT_SUPPORTED_SNS_PLATFORM);
    }

}
