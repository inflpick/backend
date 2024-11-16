package com.leesh.inflpick.product.application.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidProductImageFormat extends RuntimeException implements ErrorCode {

    public InvalidProductImageFormat() {
        super("Product image format is not supported");
    }

    public InvalidProductImageFormat(String originalFilename) {
        super("Product image format is not supported, file: %s".formatted(originalFilename));
    }

    @Override
    public String getCode() {
        return "INVALID_PRODUCT_IMAGE_FORMAT";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "상품 이미지 형식이 지원되지 않습니다.";
    }

    @Override
    public String getAction() {
        return "지원되는 상품 이미지 형식을 확인 후 다시 시도해주세요.";
    }

    @Override
    public String getComment() {
        return "상품 이미지 형식이 지원되지 않는 경우 발생합니다.";
    }
}
