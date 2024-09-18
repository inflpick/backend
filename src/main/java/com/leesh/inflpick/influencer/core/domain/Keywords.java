package com.leesh.inflpick.influencer.core.domain;

import com.leesh.inflpick.keyword.core.domain.Keyword;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public record Keywords(@NotNull List<Keyword> keywords) {

    public Keywords(@NotNull List<Keyword> keywords) {
        this.keywords = new ArrayList<>(keywords);
    }

}
