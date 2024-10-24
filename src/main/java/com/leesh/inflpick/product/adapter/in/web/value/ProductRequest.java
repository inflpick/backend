package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;
import com.leesh.inflpick.product.adapter.in.web.docs.ProductRequestApiDocs;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLink;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLinks;
import com.leesh.inflpick.product.core.domain.value.ProductDescription;
import com.leesh.inflpick.product.core.domain.value.ProductName;
import com.leesh.inflpick.product.port.ProductCommand;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

@Builder
public record ProductRequest(
        String name,
        String description,
        List<String> keywordUuids,
        List<OnlineStoreLinkRequest> onlineStoreLinks) implements ProductRequestApiDocs {

    public ProductRequest(@Nullable String name,
                          @Nullable String description,
                          @Nullable List<String> keywordUuids,
                          @Nullable List<OnlineStoreLinkRequest> onlineStoreLinks) {
        RequiredFieldsValidator.validate(name, description);
        assert name != null;
        this.name = name.strip();
        assert description != null;
        this.description = description.strip();
        this.keywordUuids = keywordUuids == null ? List.of() : keywordUuids.stream().map(String::strip).toList();
        this.onlineStoreLinks = onlineStoreLinks == null ? List.of() : onlineStoreLinks.stream()
                .map(onlineStoreLinkRequest -> new OnlineStoreLinkRequest(onlineStoreLinkRequest.platform().strip(), onlineStoreLinkRequest.url().strip()))
                .toList();
    }

    public @NotNull ProductCommand toCommand() {
        RequiredFieldsValidator.validate(name, description);
        ProductName productName = ProductName.from(name);
        ProductDescription productDescription = ProductDescription.from(description);
        return new ProductCommand(
            productName,
            productDescription,
            new HashSet<>(keywordUuids),
            convertToOnlineStoreEntity(onlineStoreLinks)
        );
    }

    public OnlineStoreLinks convertToOnlineStoreEntity(List<OnlineStoreLinkRequest> onlineStoreLinks) {
        List<OnlineStoreLink> links = onlineStoreLinks.stream()
                .map(OnlineStoreLinkRequest::toEntity)
                .toList();
        return new OnlineStoreLinks(new HashSet<>(links));
    }

}
