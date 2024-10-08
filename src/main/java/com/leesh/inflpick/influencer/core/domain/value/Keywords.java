package com.leesh.inflpick.influencer.core.domain.value;

import com.leesh.inflpick.influencer.core.domain.exception.KeywordMaximumSizeExceedException;
import com.leesh.inflpick.keyword.core.domain.Keyword;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record Keywords(@NotNull Collection<Keyword> keywords) {

    public static final Integer MAX_KEYWORD_SIZE = 10;
    public static final Keywords EMPTY = new Keywords(new HashSet<>());

    public Keywords(@NotNull Collection<Keyword> keywords) {
        validateMaximumSize(0, keywords.size());
        this.keywords = new HashSet<>(keywords);
    }

    public void addAll(Keywords keywords) throws KeywordMaximumSizeExceedException {
        int currentSize = this.getSize();
        validateMaximumSize(currentSize, keywords.getSize());
        this.keywords.addAll(keywords.keywords);
    }

    private static void validateMaximumSize(int currentSize, int requestSize) throws KeywordMaximumSizeExceedException {
        if (currentSize + requestSize > MAX_KEYWORD_SIZE) {
            throw new KeywordMaximumSizeExceedException(currentSize, requestSize);
        }
    }

    private int getSize() {
        return this.keywords.size();
    }

    private boolean contains(Keyword keyword) {
        return keywords.contains(keyword);
    }

    private boolean isFull() {
        return keywords.size() >= 10;
    }

    public Set<String> getIds() {
        return keywords.stream()
                .map(Keyword::getId)
                .collect(Collectors.toSet());
    }

    public Keywords subset(Collection<String> keywordIds) {
        Set<Keyword> subset = keywords.stream()
                .filter(keyword -> keywordIds.contains(keyword.getId()))
                .collect(Collectors.toSet());
        return new Keywords(subset);
    }
}
