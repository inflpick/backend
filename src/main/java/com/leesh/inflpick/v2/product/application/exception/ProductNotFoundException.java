package com.leesh.inflpick.v2.product.application.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends IllegalArgumentException implements ErrorCode {

    public ProductNotFoundException() {
        // SwaggerConfig에서 기본 생성자를 호출하기 때문에, 필요
    }

    public ProductNotFoundException(ProductId id) {
        super("Product Not Found, id: " + id.id());
    }

    @Override
    public String getCode() {
        return "PRODUCT_NOT_FOUND";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getReason() {
        return "상품을 찾을 수 없어요.";
    }

    @Override
    public String getAction() {
        return "상품 ID를 확인 후, 다시 시도해주세요.";
    }

    @Override
    public String getComment() {
        return "상품 ID가 존재하지 않는 경우에 발생합니다.";
    }
}
