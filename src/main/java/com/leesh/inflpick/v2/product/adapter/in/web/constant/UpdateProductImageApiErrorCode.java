package com.leesh.inflpick.v2.product.adapter.in.web.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UpdateProductImageApiErrorCode implements ApiErrorCode {

    INVALID_PRODUCT_IMAGE_REQUEST("UPI_0001", HttpStatus.BAD_REQUEST, "이미지 파일이 아니에요.", "이미지 파일만 업로드 할 수 있어요.", "주로 파일의 이름이 비어있거나, 파일 내용에 잘못된 문자 형식으로 인해 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    UpdateProductImageApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }
}
