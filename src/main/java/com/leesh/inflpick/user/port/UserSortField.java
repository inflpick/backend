package com.leesh.inflpick.user.port;
import lombok.Getter;

@Getter
public enum UserSortField {

    NICKNAME("nickname"),
    CREATED_DATE("createdDate"),
    LAST_MODIFIED_DATE("lastModifiedDate");
    ;

    private final String value;

    UserSortField(String value) {
        this.value = value;
    }
}
