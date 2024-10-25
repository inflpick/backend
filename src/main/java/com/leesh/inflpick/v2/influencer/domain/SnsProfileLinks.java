package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
public final class SnsProfileLinks {

    private final Set<SnsProfileLink> links;

    private SnsProfileLinks() {
        this.links = Set.of();
    }

    private SnsProfileLinks(Set<SnsProfileLink> links) {
        this.links = Collections.unmodifiableSet(links);
    }

    /* Business Logic */
    static SnsProfileLinks create(Set<SnsProfileLink> links) {
        return new SnsProfileLinks(links);
    }

    static SnsProfileLinks empty() {
        return new SnsProfileLinks();
    }

    void add(SnsProfileLink link) {
        links.add(link);
    }

    SnsProfileLinks addAll(Set<SnsProfileLink> links) {
        Set<SnsProfileLink> newLinks = new HashSet<>(this.links);
        newLinks.addAll(links);
        return new SnsProfileLinks(newLinks);
    }

    boolean contains(SnsProfileLink snsProfileLink) {
        return links.contains(snsProfileLink);
    }
}
