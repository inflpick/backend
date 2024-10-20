package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.exception.InvalidSortParameterException;
import com.leesh.inflpick.common.adapter.in.web.exception.MissingRequiredFieldsException;
import com.leesh.inflpick.common.adapter.in.web.exception.UnauthorizedException;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import com.leesh.inflpick.common.port.in.exception.InvalidDirectionException;
import com.leesh.inflpick.common.port.in.exception.InvalidPageNumberException;
import com.leesh.inflpick.common.port.in.exception.InvalidPageSizeException;
import com.leesh.inflpick.common.port.in.exception.NotImageTypeException;
import com.leesh.inflpick.common.port.out.exception.InvalidFileRequestException;
import com.leesh.inflpick.common.port.out.exception.ThirdPartyStorageException;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerCreateApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerProfileImageUpdateApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReviewsApiErrorCode;
import com.leesh.inflpick.influencer.core.domain.exception.InfluencerDescriptionValidationFailedException;
import com.leesh.inflpick.influencer.core.domain.exception.InfluencerIntroductionValidationFailedException;
import com.leesh.inflpick.influencer.core.domain.exception.InfluencerNameValidationFailedException;
import com.leesh.inflpick.influencer.core.domain.exception.InvalidSocialMediaPlatformException;
import com.leesh.inflpick.influencer.port.out.InfluencerNotFoundException;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordCreateApiErrorCode;
import com.leesh.inflpick.keyword.core.domain.HexColorSyntaxException;
import com.leesh.inflpick.keyword.core.domain.KeywordNameValidationFailedException;
import com.leesh.inflpick.keyword.port.in.DuplicateKeywordNameException;
import com.leesh.inflpick.product.adapter.in.web.value.ProductCreateApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.product.core.domain.exception.InvalidOnlineStoreException;
import com.leesh.inflpick.product.core.domain.exception.ProductDescriptionValidationFailedException;
import com.leesh.inflpick.product.core.domain.exception.ProductNameValidationFailedException;
import com.leesh.inflpick.product.port.out.ProductNotFoundException;
import com.leesh.inflpick.review.core.domain.ReviewContentsValidationFailedException;
import com.leesh.inflpick.review.core.domain.ReviewUriValidationFailedException;
import com.leesh.inflpick.user.adapter.in.web.Oauth2LoginApiErrorCode;
import com.leesh.inflpick.user.port.out.NotSupportedOauth2TypeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Order()
@RestControllerAdvice
public class ExceptionControllerAdvice {

    public static ResponseEntity<ApiErrorResponse> createResponseEntityFromApiErrorCode(HttpServletRequest request, ApiErrorCode apiErrorCode) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                Instant.now(),
                apiErrorCode.httpStatus().value(),
                apiErrorCode.code(),
                apiErrorCode.reason(),
                apiErrorCode.action(),
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(apiErrorCode.httpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiErrorResponse);
    }

    private Optional<MissingRequiredFieldsException> findMissingRequiredFieldsException(Throwable e) {
        while (e != null) {
            if (e instanceof MissingRequiredFieldsException cause) {
                return Optional.of(cause);
            } else {
                e = e.getCause();
            }
        }
        return Optional.empty();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handlerRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("RuntimeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.SERVER_ERROR);
    }

    @ExceptionHandler(MissingRequiredFieldsException.class)
    public ResponseEntity<ApiErrorResponse> handlerMissingRequiredFieldsException(MissingRequiredFieldsException e, HttpServletRequest request) {
         log.error("MissingRequiredFieldsException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.MISSING_REQUIRED_FIELDS);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage(), e);

        return findMissingRequiredFieldsException(e)
                .map(exception -> createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.MISSING_REQUIRED_FIELDS))
                .orElseGet(() -> createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_REQUEST_BODY));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.error("HttpMediaTypeNotSupportedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.UNSUPPORTED_HTTP_MEDIA_TYPE);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ApiErrorResponse> handlerMissingServletRequestPartException(MissingServletRequestPartException e, HttpServletRequest request) {
        log.error("MissingServletRequestPartException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.MISSING_REQUEST_PART);
    }

    @ExceptionHandler(NotImageTypeException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotImageTypeException(NotImageTypeException e, HttpServletRequest request) {
        log.error("NotImageTypeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.NOT_IMAGE_TYPE);
    }

    @ExceptionHandler(InvalidPageNumberException.class)
    public ResponseEntity<ApiErrorResponse> handlerWrongPageException(InvalidPageNumberException e, HttpServletRequest request) {
        log.error("WrongPageException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_PAGE_VALUE);
    }

    @ExceptionHandler(InvalidPageSizeException.class)
    public ResponseEntity<ApiErrorResponse> handlerWrongPageSizeException(InvalidPageSizeException e, HttpServletRequest request) {
        log.error("WrongPageSizeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_PAGE_SIZE_VALUE);
    }

    @ExceptionHandler(InvalidDirectionException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidDirectionException(InvalidDirectionException e, HttpServletRequest request) {
        log.error("InvalidDirectionException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_SORT_DIRECTION);
    }

    @ExceptionHandler(InvalidSortParameterException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidSortParameterException(InvalidSortParameterException e, HttpServletRequest request) {
        log.error("InvalidSortParameterException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_SORT_PARAMETER);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerNoResourceFoundException(NoResourceFoundException e, HttpServletRequest request) {
        log.error("NoResourceFoundException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.NOT_FOUND_API_URL);
    }

    @ExceptionHandler(InfluencerNameValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerNameValidationFailedException(InfluencerNameValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerNameValidationFailedException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_NAME_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerIntroductionValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerIntroductionValidationFailedException(InfluencerIntroductionValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerIntroductionValidationFailedException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_INTRODUCTION_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerDescriptionValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerDescriptionValidationFailedException(InfluencerDescriptionValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerDescriptionValidationFailedException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_DESCRIPTION_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InvalidSocialMediaPlatformException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidSocialMediaPlatformException(InvalidSocialMediaPlatformException e, HttpServletRequest request) {
        log.warn("InvalidSocialMediaPlatformException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INVALID_SOCIAL_MEDIA_TYPE;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerNotFoundException(InfluencerNotFoundException e, HttpServletRequest request) {
        log.warn("InfluencerNotFoundException: {}", e.getMessage(), e);
        InfluencerReadApiErrorCode apiErrorCode = InfluencerReadApiErrorCode.INFLUENCER_NOT_FOUND;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InvalidFileRequestException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidFileRequestException(InvalidFileRequestException e, HttpServletRequest request) {
        log.warn("InvalidFileRequestException: {}", e.getMessage(), e);
        InfluencerProfileImageUpdateApiErrorCode apiErrorCode = InfluencerProfileImageUpdateApiErrorCode.INVALID_PROFILE_IMAGE_REQUEST;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(ThirdPartyStorageException.class)
    public ResponseEntity<ApiErrorResponse> handlerThirdPartyStorageException(ThirdPartyStorageException e, HttpServletRequest request) {
        log.warn("ThirdPartyStorageException: {}", e.getMessage(), e);
        InfluencerProfileImageUpdateApiErrorCode apiErrorCode = InfluencerProfileImageUpdateApiErrorCode.PROFILE_IMAGE_UPLOAD_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(ReviewContentsValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerReviewContentsValidationFailedException(ReviewContentsValidationFailedException e, HttpServletRequest request) {
        log.warn("ReviewContentsValidationFailedException: {}", e.getMessage(), e);
        InfluencerReviewsApiErrorCode apiErrorCode = InfluencerReviewsApiErrorCode.REVIEW_CONTENTS_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(ReviewUriValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerReviewUriValidationFailedException(ReviewUriValidationFailedException e, HttpServletRequest request) {
        log.warn("ReviewUriValidationFailedException: {}", e.getMessage(), e);
        InfluencerReviewsApiErrorCode apiErrorCode = InfluencerReviewsApiErrorCode.REVIEW_URI_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerProductNotFoundException(ProductNotFoundException e, HttpServletRequest request) {
        log.warn("ProductNotFoundException: {}", e.getMessage(), e);
        ProductReadApiErrorCode apiErrorCode = ProductReadApiErrorCode.PRODUCT_NOT_FOUND;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(KeywordNameValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerKeywordNameValidationFailedException(KeywordNameValidationFailedException e, HttpServletRequest request) {
        log.error("KeywordNameValidationFailedException: {}", e.getMessage(), e);
        KeywordCreateApiErrorCode apiErrorCode = KeywordCreateApiErrorCode.KEYWORD_NAME_VALIDATE_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(HexColorSyntaxException.class)
    public ResponseEntity<ApiErrorResponse> handlerHexColorSyntaxException(HexColorSyntaxException e, HttpServletRequest request) {
        log.error("HexColorSyntaxException: {}", e.getMessage(), e);
        KeywordCreateApiErrorCode apiErrorCode = KeywordCreateApiErrorCode.KEYWORD_COLOR_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(DuplicateKeywordNameException.class)
    public ResponseEntity<ApiErrorResponse> handlerDuplicateKeywordNameException(DuplicateKeywordNameException e, HttpServletRequest request) {
        log.error("DuplicateKeywordNameException: {}", e.getMessage(), e);
        KeywordCreateApiErrorCode apiErrorCode = KeywordCreateApiErrorCode.DUPLICATE_KEYWORD_NAME_EXCEPTION;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

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

    @ExceptionHandler(NotSupportedOauth2TypeException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotSupportedOauth2TypeException(NotSupportedOauth2TypeException e, HttpServletRequest request) {
        log.warn("NotSupportedOauth2TypeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, Oauth2LoginApiErrorCode.NOT_SUPPORTED_OAUTH2_TYPE);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handlerAuthorizationDeniedException(AuthorizationDeniedException e, HttpServletRequest request) {
        log.warn("AuthorizationDeniedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handlerUnauthorizedException(UnauthorizedException e, HttpServletRequest request) {
        log.warn("UnauthorizedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.UNAUTHORIZED);
    }

}
