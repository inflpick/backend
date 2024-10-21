package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;

import java.util.HashSet;
import java.util.Set;

public final class SnsProfileLinks {

    private final Set<SnsProfileLink> links;

    private SnsProfileLinks() {
        this.links = Set.of();
    }

    private SnsProfileLinks(Set<SnsProfileLink> links) {
        this.links = new HashSet<>(links);
    }

    /* Business Logic */
    static SnsProfileLinks create(Set<SnsProfileLink> links) {
        return new SnsProfileLinks(Set.copyOf(links));
    }

    static SnsProfileLinks empty() {
        return new SnsProfileLinks();
    }

    Set<SnsProfileLink> getLinks() {
        return Set.copyOf(links);
    }

    void add(SnsProfileLink link) {
        links.add(link);
    }

}
