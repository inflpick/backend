package com.leesh.inflpick.user.adapter.out.persistence.mongo;

import com.leesh.inflpick.common.adapter.out.persistence.PageSortConverter;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.user.core.domain.User;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public record UserPageResponse(Collection<User> contents,
                               Integer currentPage,
                               Integer totalPages,
                               Integer size,
                               Long totalElements,
                               String[] sortProperties) implements PageResponse<User> {
    @Override
    public Collection<User> contents() {
        return contents;
    }

    @Override
    public Integer currentPage() {
        return currentPage;
    }

    @Override
    public Integer totalPages() {
        return totalPages;
    }

    @Override
    public Integer size() {
        return size;
    }

    @Override
    public Long totalElements() {
        return totalElements;
    }

    @Override
    public String[] sortProperties() {
        return sortProperties;
    }

    public static UserPageResponse from(Page<UserDocument> documentPage) {

        List<User> contents = documentPage.getContent().stream()
                .map(UserDocument::toEntity)
                .toList();

        return new UserPageResponse(contents,
                documentPage.getNumber(),
                documentPage.getTotalPages(),
                documentPage.getSize(),
                documentPage.getTotalElements(),
                PageSortConverter.convertSortProperties(documentPage.getSort()));
    }
}
