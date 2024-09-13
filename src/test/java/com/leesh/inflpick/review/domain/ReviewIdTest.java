package com.leesh.inflpick.review.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewIdTest {

    @Test
    @DisplayName("유효한 UUID로 ReviewId 생성되어야 한다.")
    void reviewIdCreationWithValidUuid() {
        ReviewId reviewId = new ReviewId("valid-uuid");
        assertNotNull(reviewId);
        assertEquals("valid-uuid", reviewId.uuid());
    }

    @Test
    @DisplayName("null UUID로 ReviewId 생성 시 예외가 발생해야 한다.")
    void reviewIdCreationWithNullUuidThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new ReviewId(null);
        });
    }

    @Test
    @DisplayName("빈 UUID로 ReviewId 생성 시 예외가 발생해야 한다.")
    void reviewIdCreationWithBlankUuidThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ReviewId("");
        });
        assertEquals("uuid must not be blank", exception.getMessage());
    }
}