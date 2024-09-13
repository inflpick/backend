package com.leesh.inflpick.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    @DisplayName("유효한 UUID로 ProductId 생성되어야 한다.")
    void productIdCreationWithValidUuid() {
        ProductId productId = new ProductId("valid-uuid");
        assertNotNull(productId);
        assertEquals("valid-uuid", productId.uuid());
    }

    @Test
    @DisplayName("null UUID로 ProductId 생성 시 예외가 발생해야 한다.")
    void productIdCreationWithNullUuidThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new ProductId(null);
        });
    }

    @Test
    @DisplayName("빈 UUID로 ProductId 생성 시 예외가 발생해야 한다.")
    void productIdCreationWithBlankUuidThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProductId("");
        });
        assertEquals("uuid must not be blank", exception.getMessage());
    }
}