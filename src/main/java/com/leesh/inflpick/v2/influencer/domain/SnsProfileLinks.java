package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record SnsProfileLinks(List<SnsProfileLink> links) {

    public SnsProfileLinks {
        links = Collections.unmodifiableList(links);
    }

    /* Business Logic */
    public static SnsProfileLinks create(List<SnsProfileLink> links) {
        return new SnsProfileLinks(links);
    }

    public static SnsProfileLinks empty() {
        return new SnsProfileLinks(new ArrayList<>());
    }
}
