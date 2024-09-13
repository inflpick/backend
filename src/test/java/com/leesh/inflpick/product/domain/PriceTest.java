package com.leesh.inflpick.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    @DisplayName("유효한 값으로 Price 생성되어야 한다.")
    void priceCreationWithValidValue() {
        Price price = new Price(100);
        assertNotNull(price);
        assertEquals(100, price.value());
    }

    @Test
    @DisplayName("null 값으로 Price 생성 시 예외가 발생해야 한다.")
    void priceCreationWithNullValueThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new Price(null);
        });
    }

    @Test
    @DisplayName("음수 값으로 Price 생성 시 예외가 발생해야 한다.")
    void priceCreationWithNegativeValueThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Price(-1);
        });
        assertEquals("value must be greater than or equal to 0", exception.getMessage());
    }
}