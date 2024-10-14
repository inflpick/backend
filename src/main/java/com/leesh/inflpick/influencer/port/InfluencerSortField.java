package com.leesh.inflpick.influencer.port;

import lombok.Getter;

@Getter
public enum InfluencerSortField {

    NAME("name"),
    CREATED_DATE("createdDate"),
    LAST_MODIFIED_DATE("lastModifiedDate"),
    ;

    private final String value;

    InfluencerSortField(String value) {
        this.value = value;
    }
}
