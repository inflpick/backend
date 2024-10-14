package com.leesh.inflpick.product.port;

import lombok.Getter;

@Getter
public enum ProductSortProperty {

    NAME("name"),
    CREATED_DATE("createdDate"),
    LAST_MODIFIED_DATE("lastModifiedDate"),
    ;

    private final String value;

    ProductSortProperty(String value) {
        this.value = value;
    }
}
