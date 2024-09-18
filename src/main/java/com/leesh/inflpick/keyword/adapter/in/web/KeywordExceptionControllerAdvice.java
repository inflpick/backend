package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorResponse;
import com.leesh.inflpick.keyword.core.domain.HexColorSyntaxException;
import com.leesh.inflpick.keyword.core.domain.KeywordNameValidationFailedException;
import com.leesh.inflpick.keyword.port.in.DuplicateKeywordNameException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.common.adapter.in.web.CommonExceptionControllerAdvice.createResponseEntityFromApiErrorCode;

@Slf4j
@RestControllerAdvice
public class KeywordExceptionControllerAdvice {

    @ExceptionHandler(KeywordNameValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerKeywordNameValidationFailedException(KeywordNameValidationFailedException e, HttpServletRequest request) {
        log.error("KeywordNameValidationFailedException: {}", e.getMessage());
        KeywordCreateApiErrorCode apiErrorCode = KeywordCreateApiErrorCode.KEYWORD_NAME_VALIDATE_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(HexColorSyntaxException.class)
    public ResponseEntity<ApiErrorResponse> handlerHexColorSyntaxException(HexColorSyntaxException e, HttpServletRequest request) {
        log.error("HexColorSyntaxException: {}", e.getMessage());
        KeywordCreateApiErrorCode apiErrorCode = KeywordCreateApiErrorCode.KEYWORD_COLOR_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(DuplicateKeywordNameException.class)
    public ResponseEntity<ApiErrorResponse> handlerDuplicateKeywordNameException(DuplicateKeywordNameException e, HttpServletRequest request) {
        log.error("DuplicateKeywordNameException: {}", e.getMessage());
        KeywordCreateApiErrorCode apiErrorCode = KeywordCreateApiErrorCode.DUPLICATE_KEYWORD_NAME_EXCEPTION;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

}
