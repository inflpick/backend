package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public final class SnsProfileLinks {

    private final List<SnsProfileLink> links;

    private SnsProfileLinks() {
        this.links = List.of();
    }

    private SnsProfileLinks(List<SnsProfileLink> links) {
        this.links = Collections.unmodifiableList(links);
    }

    /* Business Logic */
    static SnsProfileLinks create(List<SnsProfileLink> links) {
        return new SnsProfileLinks(links);
    }

    static SnsProfileLinks empty() {
        return new SnsProfileLinks();
    }
}
