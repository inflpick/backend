package com.leesh.inflpick.keyword.domain;

import com.leesh.inflpick.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.keyword.domain.vo.KeywordColor;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.keyword.domain.vo.KeywordName;

import java.time.Instant;
import java.util.Objects;

public final class Keyword {
    private final KeywordId id;
    private final KeywordName name;
    private final KeywordColor color;
    private final Instant createdDate;
    private final String createdBy;
    private final Instant lastModifiedDate;
    private final String lastModifiedBy;

    public Keyword(KeywordId id,
                   KeywordName name,
                   KeywordColor color,
                   Instant createdDate,
                   String createdBy,
                   Instant lastModifiedDate,
                   String lastModifiedBy) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }

    /* Business Logic */
    public static Keyword withId(KeywordId id,
                                 KeywordName name,
                                 KeywordColor color,
                                 Instant createdDate,
                                 String createdBy,
                                 Instant lastModifiedDate,
                                 String lastModifiedBy) {
        return new Keyword(id, name, color, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
    }

    public static Keyword withoutId(String name, String color) {
        KeywordId keywordId = KeywordId.empty();
        KeywordName keywordName = KeywordName.create(name);
        KeywordColor keywordColor = KeywordColor.create(color);
        return new Keyword(keywordId, keywordName, keywordColor, null, null, null, null);
    }

    public Keyword update(KeywordRequest request) {
        KeywordName keywordName = KeywordName.create(request.name());
        KeywordColor keywordColor = KeywordColor.create(request.hexColor());
        return withId(id, keywordName, keywordColor, createdDate, createdBy, Instant.now(), lastModifiedBy);
    }

    public KeywordId id() {
        return id;
    }

    public KeywordName name() {
        return name;
    }

    public KeywordColor color() {
        return color;
    }

    public Instant createdDate() {
        return createdDate;
    }

    public String createdBy() {
        return createdBy;
    }

    public Instant lastModifiedDate() {
        return lastModifiedDate;
    }

    public String lastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Keyword) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
