package com.leesh.inflpick.product.core.value;

import lombok.Getter;

@Getter
public enum Category {

    CLOTHING("의류"),
    SHOES("신발"),
    BAGS("가방"),
    ACCESSORIES("액세서리"),
    COSMETICS("화장품"),
    ELECTRONICS("전자 제품"),
    HOME("가전 제품"),
    BOOKS("도서"),
    HEALTH("건강 용품"),
    SPORTS("스포츠"),
    ETC("기타");

    private final String description;

    Category(String description) {
        this.description = description;
    }
}
