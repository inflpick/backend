package com.leesh.inflpick.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryTest {

    @Test
    @DisplayName("유효한 값으로 Category 생성되어야 한다.")
    void categoryCreationWithValidValues() {
        Category category = Category.CLOTHING;
        assertNotNull(category);
        assertEquals("의류", category.getDescription());
    }

    @Test
    @DisplayName("Category의 description이 null이 아니어야 한다.")
    void categoryDescriptionShouldNotBeNull() {
        for (Category category : Category.values()) {
            assertNotNull(category.getDescription());
        }
    }

    @Test
    @DisplayName("Category의 description이 올바른 값이어야 한다.")
    void categoryDescriptionShouldBeCorrect() {
        assertEquals("의류", Category.CLOTHING.getDescription());
        assertEquals("신발", Category.SHOES.getDescription());
        assertEquals("가방", Category.BAGS.getDescription());
        assertEquals("액세서리", Category.ACCESSORIES.getDescription());
        assertEquals("화장품", Category.COSMETICS.getDescription());
        assertEquals("전자 제품", Category.ELECTRONICS.getDescription());
        assertEquals("가전 제품", Category.HOME.getDescription());
        assertEquals("도서", Category.BOOKS.getDescription());
        assertEquals("건강 용품", Category.HEALTH.getDescription());
        assertEquals("스포츠", Category.SPORTS.getDescription());
        assertEquals("기타", Category.ETC.getDescription());
    }
}